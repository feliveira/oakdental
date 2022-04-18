package com.fiap.oak.oakdental.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_LOJA")
public class Loja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String path;

    @OneToMany
    private List<Produto> produtos = new ArrayList<>();

    public Loja(){

    }

    public Loja(Long id, String nome, String path) {
        this.id = id;
        this.nome = nome;
        this.path = path;
    }

    public Loja(Long id, String nome, String path, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.path = path;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", path='" + path + '\'' +
                ", produtos=" + produtos +
                '}';
    }
}
