package com.thiagoasd.ecommercebackend.Controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.thiagoasd.ecommercebackend.Domain.Pedido;
import com.thiagoasd.ecommercebackend.Service.PedidoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping("/pedido")
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid Pedido pedido) {
		try {
			return ResponseEntity.ok(pedidoService.salvar(pedido));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@GetMapping("/pedido")
	public ResponseEntity<List<Pedido>> listaPedido() {

		return ResponseEntity.ok(pedidoService.list());
	}

	@GetMapping("/pedido/{id}")
	public ResponseEntity<Pedido> buscaProduto(@PathVariable int id) {

		try {
			return ResponseEntity.ok(pedidoService.consultaPorId(id));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/pedido")
	public ResponseEntity<Pedido> update(@RequestBody @Valid Pedido pedido) {
		try {
			return ResponseEntity.ok(pedidoService.update(pedido.getID(), pedido));
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<Boolean> deletePorId(@PathVariable int id) {
		try {
			pedidoService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
