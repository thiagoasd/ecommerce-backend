package com.thiagoasd.ecommercebackend.Service;

import org.springframework.stereotype.Service;

import com.thiagoasd.ecommercebackend.Domain.Produto;
import com.thiagoasd.ecommercebackend.Repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import javassist.NotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto consultaPorId(int id) throws NotFoundException {
		Optional<Produto> deptOpt = produtoRepository.findById(id);

		if (deptOpt.isEmpty()) {
			throw new NotFoundException("Produto não localizado");
		}

		return deptOpt.get();

	}

	public List<Produto> list() {
		return produtoRepository.findAll();
	}

	public Produto update(int id, Produto produto) throws NotFoundException {
		consultaPorId(id);
		produto.setID(id); 
		return produtoRepository.save(produto);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		produtoRepository.deleteById(id);
	}

}