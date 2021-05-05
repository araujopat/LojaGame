package com.lojagamer.lojagamer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojagamer.lojagamer.repository.ProdutoRespository;


@RestController 
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping ("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRespository repositorio;
	

}
