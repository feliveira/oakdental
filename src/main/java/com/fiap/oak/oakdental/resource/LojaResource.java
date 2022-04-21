package com.fiap.oak.oakdental.resource;

import com.fiap.oak.oakdental.domain.Loja;
import com.fiap.oak.oakdental.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lojas")
public class LojaResource {

    @Autowired
    LojaRepository repository;

    @GetMapping
    public ResponseEntity<List<Loja>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Loja> findById(@PathVariable("id") Long id) {
        Loja loja;
        try{
            loja = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(loja);
    }

    @GetMapping("/findByNome/{nome}")
    public ResponseEntity<List<Loja>> findByNomeContainingIgnoreCase(@PathVariable("nome") String nome) {
        return ResponseEntity.ok().body(repository.findByNomeIgnoreCaseContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Loja> insert(@RequestBody Loja loja){
        loja = repository.save(loja);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(loja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loja> alter(@PathVariable("id") Long id, @RequestBody Loja loja) {
        Loja obj;
        try{
            obj = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        if(loja.getNome() != null) {
            obj.setNome(loja.getNome());
        }
        if(loja.getPath() != null){
            obj.setPath(loja.getPath());
        }
        if(loja.getProdutos() != null) {
            obj.getProdutos().addAll(loja.getProdutos());
        }

        repository.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Loja loja;
        try{
            loja = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        repository.delete(loja);
        return ResponseEntity.noContent().build();
    }
}
