package com.example.springboot.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.ProdutoDTO;
import com.example.springboot.model.ProdutoModel;
import com.example.springboot.repositories.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@PostMapping("/produto")
	public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {

		var produtoModel = new ProdutoModel();

		BeanUtils.copyProperties(produtoDTO, produtoModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
	}
	
	@GetMapping("/produto")
	public List<ProdutoModel> listarProdutos(){
		return produtoRepository.findAll();
	}
	/*
	@GetMapping("/produto")
	public ResponseEntity<Page<ProdutoModel>> listarProdutos(Pageable pageable){
		Page<ProdutoModel> produtos = produtoRepository.findAll(pageable);
		
		return ResponseEntity.ok().body(produtos);
	}*/
	
    @DeleteMapping("produto/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable UUID id) {
    	produtoRepository.deleteById(id);
    	return ResponseEntity.ok().body("Deletado");
    }
    
    @PutMapping("editar-produto/{id}")
    public ResponseEntity<String> updateId(@RequestBody @Valid ProdutoDTO produtoDTO,@PathVariable UUID id) {
    	produtoRepository.deleteById(id);
    	
		var produtoModel = new ProdutoModel();

		BeanUtils.copyProperties(produtoDTO, produtoModel);
		produtoRepository.save(produtoModel);
		
		return ResponseEntity.ok().body("Editado");
    }
    
}
