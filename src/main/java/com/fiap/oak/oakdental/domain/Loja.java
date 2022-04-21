package com.fiap.oak.oakdental.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Produto> produtos = new HashSet<>();

    public Loja(){

    }

    public Loja(Long id, String nome, String path) {
        this.id = id;
        this.nome = nome;
        this.path = path;
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

    public Set<Produto> getProdutos() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loja loja = (Loja) o;

        return id.equals(loja.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
