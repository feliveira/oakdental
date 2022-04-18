package com.fiap.oak.oakdental.config;

import com.fiap.oak.oakdental.domain.Loja;
import com.fiap.oak.oakdental.domain.Produto;
import com.fiap.oak.oakdental.repository.LojaRepository;
import com.fiap.oak.oakdental.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    LojaRepository lojaRepository;

    @Override
    public void run(String... args) throws Exception {

        Loja l1 = new Loja(null,"Sorriso de Neve","www.sorrisodeneve.com.br");
        Loja l2 = new Loja(null, "Sorria Dental", "www.sorriadental.com.br");
        Loja l3 = new Loja(null, "Belo Sorriso", "www.belosorriso.com.br");

        lojaRepository.saveAll(Arrays.asList(l1,l2,l3));

        Produto p1 = new Produto(null,"Escova Elétrica","A melhor do mercado, cor verde","Oral C",32.50,l1);
        Produto p2 = new Produto(null, "Cadeira Odontológica", "Cadeira muito confortável para atendimento, cor azul", "Atlante", 25550.00,l3);

        l1.getProdutos().add(p1);
        l3.getProdutos().add(p2);

        produtoRepository.saveAll(Arrays.asList(p1,p2));
        lojaRepository.saveAll(Arrays.asList(l1,l2,l3));

        
    }

}
