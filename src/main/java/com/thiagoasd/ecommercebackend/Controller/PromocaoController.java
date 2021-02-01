package com.thiagoasd.ecommercebackend.Controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.thiagoasd.ecommercebackend.Domain.Promocao;
import com.thiagoasd.ecommercebackend.Service.PromocaoService;

import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PromocaoController {

	@Autowired
	private PromocaoService promocaoService;

	@PostMapping("/promocao")
	public ResponseEntity<Promocao> salvar(@RequestBody @Valid Promocao promocao) {
		try {
			return ResponseEntity.ok(promocaoService.salvar(promocao));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@GetMapping("/promocao")
	public ResponseEntity<List<Promocao>> listaPromocao() {

		return ResponseEntity.ok(promocaoService.list());
	}

	@GetMapping("/promocao/{id}")
	public ResponseEntity<Promocao> buscaProduto(@PathVariable int id) {

		try {
			return ResponseEntity.ok(promocaoService.consultaPorId(id));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/promocao")
	public ResponseEntity<Promocao> update(@RequestBody @Valid Promocao promocao) {
		try {
			return ResponseEntity.ok(promocaoService.update(promocao.getID(), promocao));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@DeleteMapping("/promocao/{id}")
	public ResponseEntity<Boolean> deletePorId(@PathVariable int id) {
		try {
			promocaoService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
