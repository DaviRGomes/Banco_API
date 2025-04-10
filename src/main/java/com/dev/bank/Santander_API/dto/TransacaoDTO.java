package com.dev.bank.Santander_API.dto;
import com.dev.bank.Santander_API.entity.TipoTransacoes ;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransacaoDTO {
    private BigDecimal valor;
    private String descricao;
    private TipoTransacoes tipo;
    private Long clienteId;
    private Long cartaoId; // para transações com cartão
    private Long destinatarioId; // para Pix

    // Getters e Setters
}
