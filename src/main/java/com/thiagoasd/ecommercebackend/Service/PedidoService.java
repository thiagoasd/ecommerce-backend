package com.thiagoasd.ecommercebackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagoasd.ecommercebackend.Domain.Pedido;
import com.thiagoasd.ecommercebackend.Domain.Produto;
import com.thiagoasd.ecommercebackend.Domain.ProdutoCesta;
import com.thiagoasd.ecommercebackend.Repository.PedidoRepository;
import com.thiagoasd.ecommercebackend.Repository.ProdutoRepository;

import javassist.NotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	private ProdutoRepository produtoRepository;

	public Pedido salvar(Pedido pedido) throws Exception {
		
		validatePedido(pedido);
		return pedidoRepository.save(pedido);
	}

	public Pedido consultaPorId(int id) throws NotFoundException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido.isEmpty()) {
			throw new NotFoundException("Pedido não localizado");
		}

		return pedido.get();

	}

	public List<Pedido> list() {
		return pedidoRepository.findAll();
	}

	public Pedido update(int id, Pedido pedido) throws Exception {
		consultaPorId(id);
		validatePedido(pedido);
		pedido.setID(id);
		
		return pedidoRepository.save(pedido);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		pedidoRepository.deleteById(id);
	}

	private void validatePedido(Pedido pedido) throws Exception {
		
		for (ProdutoCesta produtoCesta : pedido.getProdutos()) {
			Optional<Produto> result = produtoRepository.findById(produtoCesta.getProduto().getID());

			// Checa se o produto enviado no pedido existe mesmo
			if (result.isEmpty()) {
				throw new NotFoundException("Produto " + produtoCesta.getProduto().getNome() + " não localizado");

			} else {

				// Checa se o valor do produto enviado é o mesmo que está no banco
				if (result.get().getValor() != produtoCesta.getProduto().getValor()) {
					throw new Exception(
							"Produto" + produtoCesta.getProduto().getNome() + " com preço diferente do cadastrado");
				}

			}

		}
	}

}
