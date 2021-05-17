package com.lojagamer.lojagamer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojagamer.lojagamer.model.Produto;
import com.lojagamer.lojagamer.repository.ProdutoRespository;


@RestController 
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping ("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRespository repositorio;
	
	@GetMapping ("/{id}")
	public ResponseEntity<Produto> findByIDProduto (@PathVariable long id) {
		
		return repositorio.findById(id).map(resultado -> ResponseEntity.ok(resultado)).orElse(ResponseEntity.notFound().build());    
	}
	
	@GetMapping("/{titulo}")
	public ResponseEntity <List<Produto>> findByTituloProduto (@PathVariable String titulo) {
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo)); // ignorecase para não fazer diferença entre maiusculo e minusculo
		
	}
	@PostMapping	// criar Produto
	public ResponseEntity <Produto> post (@Valid @RequestBody Produto produto) { // @valid é para que o json aceite as coisas
		
		return ResponseEntity.status (HttpStatus.CREATED).body (repositorio.save(produto)); // status da requisição que deve ser testado no body do postman e salvo na lista categoria
	}
	@PutMapping // altera o que ja existe no produto
	public ResponseEntity <Produto> put (@RequestBody Produto produto) {
	
		return ResponseEntity.status (HttpStatus.OK).body (repositorio.save(produto));
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repositorio.deleteById(id);
	}
}
	
	