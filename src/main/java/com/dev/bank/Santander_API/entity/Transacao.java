package com.dev.bank.Santander_API.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private String descricao;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoTransacoes tipo;

    @ManyToOne
    private Card cartao;

    @ManyToOne
    private User cliente;

    @ManyToOne
    private User destinatario;

}