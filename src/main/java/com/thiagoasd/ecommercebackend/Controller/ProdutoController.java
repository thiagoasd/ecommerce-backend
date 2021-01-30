package com.thiagoasd.ecommercebackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.thiagoasd.ecommercebackend.Domain.Produto;
import com.thiagoasd.ecommercebackend.Service.ProdutoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping("/produto")
	public ResponseEntity<Produto> salvar(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(produtoService.salvar(produto));
	}

	@GetMapping("/produto")
	public ResponseEntity<List<Produto>> listaProduto() {

		return ResponseEntity.ok(produtoService.list());
	}

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> buscaProduto(@PathVariable int id) {

		try {
			return ResponseEntity.ok(produtoService.consultaPorId(id));
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/produto")
	public ResponseEntity<Produto> update(@RequestBody @Valid Produto produto) {
		try {
			return ResponseEntity.ok(produtoService.update(produto.getID(), produto));
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Boolean> deletePorId(@PathVariable int id) {
		try {
			produtoService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
