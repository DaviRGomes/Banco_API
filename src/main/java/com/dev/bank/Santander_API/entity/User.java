package com.dev.bank.Santander_API.entity;




import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Transacao> transacoesFeitas;

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL)
    private List<Transacao> transacoesRecebidas;

}