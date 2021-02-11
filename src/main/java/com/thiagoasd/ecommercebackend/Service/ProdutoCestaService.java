package com.thiagoasd.ecommercebackend.Service;

import com.thiagoasd.ecommercebackend.Domain.ProdutoCesta;
import com.thiagoasd.ecommercebackend.Repository.ProdutoCestaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ProdutoCestaService {
	
	@Autowired
	private ProdutoCestaRepository produtoCestaRepository;

	public ProdutoCesta salvar(ProdutoCesta produto) {
		return produtoCestaRepository.save(produto);
	}
	
	public List<ProdutoCesta> salvarLista(List<ProdutoCesta> produtos) {
		
		List<ProdutoCesta> savedProdutosCestas = new ArrayList<ProdutoCesta>();
		for (ProdutoCesta produtoCesta : produtos) {
			savedProdutosCestas.add(produtoCestaRepository.save(produtoCesta));
		}
		
		return savedProdutosCestas;
	}

	public ProdutoCesta consultaPorId(int id) throws NotFoundException {
		Optional<ProdutoCesta> deptOpt = produtoCestaRepository.findById(id);

		if (deptOpt.isEmpty()) {
			throw new NotFoundException("ProdutoCesta não localizado");
		}

		return deptOpt.get();

	}

	public List<ProdutoCesta> list() {
		return produtoCestaRepository.findAll();
	}

	public ProdutoCesta update(int id, ProdutoCesta produto) throws NotFoundException {
		consultaPorId(id);
		produto.setID(id); 
		return produtoCestaRepository.save(produto);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		produtoCestaRepository.deleteById(id);
	}


}