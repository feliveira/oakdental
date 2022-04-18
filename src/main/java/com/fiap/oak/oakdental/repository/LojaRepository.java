package com.fiap.oak.oakdental.repository;

import com.fiap.oak.oakdental.domain.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LojaRepository  extends JpaRepository<Loja, Integer> {
    List<Loja> findByNomeIgnoreCaseContaining(String nome);
}
