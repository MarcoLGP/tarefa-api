package com.uninter.trabalho.tarefas.trabalho_api_tarefas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "data_entrega", nullable = false)
    private LocalDateTime dataEntrega;
    @Column(name = "responsavel", nullable = false)
    private String responsavel;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
