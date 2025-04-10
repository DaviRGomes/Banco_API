package com.dev.bank.Santander_API.controller;



import com.dev.bank.Santander_API.dto.TransacaoDTO;
import com.dev.bank.Santander_API.entity.Transacao;
import com.dev.bank.Santander_API.service.TransacaoService;
import jakarta.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Transacao> createTransaction(@RequestBody TransacaoDTO dto) {
        System.out.println(dto.toString());
        return ResponseEntity.ok(transacaoService.createTransaction(dto));
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> getAll() {
        return ResponseEntity.ok(transacaoService.getAll());
    }
}