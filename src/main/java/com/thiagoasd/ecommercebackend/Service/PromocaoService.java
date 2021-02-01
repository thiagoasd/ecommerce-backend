package com.thiagoasd.ecommercebackend.Service;

import org.springframework.stereotype.Service;

import com.thiagoasd.ecommercebackend.Domain.Promocao;
import com.thiagoasd.ecommercebackend.Repository.PromocaoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import javassist.NotFoundException;

@Service
public class PromocaoService {

	@Autowired
	private PromocaoRepository promocaoRepository;

	public Promocao salvar(Promocao promocao) {
		return promocaoRepository.save(promocao);
	}

	public Promocao consultaPorId(int id) throws NotFoundException {
		Optional<Promocao> promoOpt = promocaoRepository.findById(id);

		if (promoOpt.isEmpty()) {
			throw new NotFoundException("Promocao não localizada");
		}

		return promoOpt.get();

	}

	public List<Promocao> list() {
		return promocaoRepository.findAll();
	}

	public Promocao update(int id, Promocao promocao) throws NotFoundException {
		consultaPorId(id);
		promocao.setID(id); 
		return promocaoRepository.save(promocao);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		promocaoRepository.deleteById(id);
	}

}