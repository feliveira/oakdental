package com.fiap.oak.oakdental.resource;

import com.fiap.oak.oakdental.domain.Produto;
import com.fiap.oak.oakdental.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable("id") Integer id) {
        Produto prod;
        try{
            prod = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(prod);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody Produto produto){
        produto = repository.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alter(@PathVariable("id") Integer id, @RequestBody Produto prod) {
        Produto obj;
        try{
            obj = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        obj.setNome(prod.getNome());
        obj.setDescricao(prod.getDescricao());
        obj.setMarca(prod.getMarca());
        obj.setValor(prod.getValor());
        obj.setLoja(prod.getLoja());

        repository.save(obj);

        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Produto prod;
        try{
            prod = repository.findById(id).get();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

        repository.delete(prod);
        return ResponseEntity.noContent().build();
    }
    

}
