package com.dev.bank.Santander_API.service;

import com.dev.bank.Santander_API.dto.TransacaoDTO;
import com.dev.bank.Santander_API.entity.Card;
import com.dev.bank.Santander_API.entity.Transacao;
import com.dev.bank.Santander_API.entity.User;
import com.dev.bank.Santander_API.repository.CardRepository;
import com.dev.bank.Santander_API.repository.TransacaoRepository;
import com.dev.bank.Santander_API.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dev.bank.Santander_API.entity.TipoTransacoes.CARTAO;

@Service
public class TransacaoService {

    private final TransacaoRepository transactionRepository;
    private final UserRepository userRepository;
    private final CardRepository cardRepository;

    public TransacaoService(TransacaoRepository transactionRepository,
                            UserRepository userRepository,
                            CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public Transacao createTransaction(TransacaoDTO dto) {
        User cliente = userRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        User destinatario = userRepository.findById(dto.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        Card cartao = null;
        if (dto.getTipo() == CARTAO) {
            cartao = cardRepository.findById(dto.getCartaoId())
                    .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        }

        Transacao transaction = new Transacao();
        transaction.setDescricao(dto.getDescricao());
        transaction.setTipo(dto.getTipo());
        transaction.setValor(dto.getValor());
        transaction.setCliente(cliente);
        transaction.setDestinatario(destinatario);
        transaction.setCartao(cartao); // pode ser null se não for transação com cartão

        switch (dto.getTipo()) {
            case PIX:
                if (cliente.getAccount().getBalance().compareTo(dto.getValor()) < 0) {
                    throw new RuntimeException("Saldo insuficiente para PIX");
                }
                cliente.getAccount().setBalance(
                        cliente.getAccount().getBalance().subtract(dto.getValor())
                );
                destinatario.getAccount().setBalance(
                        destinatario.getAccount().getBalance().add(dto.getValor())
                );
                break;

            case CARTAO:
                if (cartao.getSaldo().compareTo(dto.getValor()) < 0) {
                    throw new RuntimeException("Limite do cartão insuficiente");
                }
                cartao.setSaldo(cartao.getSaldo().subtract(dto.getValor()));
                break;

            case DEPOSITO:
                cliente.getAccount().setBalance(
                        cliente.getAccount().getBalance().add(dto.getValor())
                );
                break;

            case SAQUE:
                if (cliente.getAccount().getBalance().compareTo(dto.getValor()) < 0) {
                    throw new RuntimeException("Saldo insuficiente para saque");
                }
                cliente.getAccount().setBalance(
                        cliente.getAccount().getBalance().subtract(dto.getValor())
                );
                break;

            default:
                throw new IllegalArgumentException("Tipo de transação inválido");
        }

        userRepository.save(cliente);
        userRepository.save(destinatario);
        if (cartao != null) {
            cardRepository.save(cartao);
        }

        return transactionRepository.save(transaction);
    }

    public List<Transacao> getAll() {
        return transactionRepository.findAll();
    }
}
