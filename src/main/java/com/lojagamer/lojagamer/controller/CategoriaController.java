package com.lojagamer.lojagamer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.validation.Valid;

import com.lojagamer.lojagamer.model.Categoria;
import com.lojagamer.lojagamer.repository.CategoriaRepository;

@RestController
@RequestMapping ("/categoria") // endereço no postman
public class CategoriaController {

	@Autowired
	CategoriaRepository repositorio; // da acesso a CategoriaRepository
	
	@GetMapping // quando é para pegar é get
	public ResponseEntity <List <Categoria>> findAllCategoria() {// envia a mensagem dos gatinhos
		return ResponseEntity.ok(repositorio.findAll()); // estrutura
			
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Categoria> findByIDCategoria (@PathVariable long id) { // Path Variable pega a variavel id e de fato um ID
		return repositorio.findById(id).map(resultado -> ResponseEntity.ok(resultado)).orElse(ResponseEntity.notFound().build()); // map é para checar se existe ou não o id, se ok.
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity <List<Categoria>> findByNomeCategoria (@PathVariable String nome) {
		return ResponseEntity.ok(repositorio.findAllByNomeContainingIgnoreCase(nome)); // ignorecase para não fazer diferença entre maiusculo e minusculo
		
	}
	
	@PostMapping	// criar categoria
	public ResponseEntity <Categoria> post (@Valid @RequestBody Categoria categoria) { // @valid é para que o json aceite as coisas
		
		return ResponseEntity.status (HttpStatus.CREATED).body (repositorio.save(categoria)); // status da requisição que deve ser testado no body do postman e salvo na lista categoria
	}


	@PutMapping // altera o que ja existe na categoria
	public ResponseEntity <Categoria> put (@RequestBody Categoria categoria) {
	
		return ResponseEntity.status (HttpStatus.OK).body (repositorio.save(categoria));
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repositorio.deleteById(id);
	}

}