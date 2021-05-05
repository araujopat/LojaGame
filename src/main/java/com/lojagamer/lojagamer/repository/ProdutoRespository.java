package com.lojagamer.lojagamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojagamer.lojagamer.model.Produto;

public interface ProdutoRespository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByTituloContainingIgnoreCase (String titulo);
}