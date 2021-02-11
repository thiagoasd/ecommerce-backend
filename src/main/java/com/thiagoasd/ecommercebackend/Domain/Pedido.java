package com.thiagoasd.ecommercebackend.Domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Pedido {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	
	@OneToMany(targetEntity=ProdutoCesta.class, fetch = FetchType.EAGER)
	List<ProdutoCesta> produtos;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public List<ProdutoCesta> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoCesta> produtos) {
		this.produtos = produtos;
	}
	
	public void addProduto(ProdutoCesta produto) {
		this.produtos.add(produto);
	}
	
	public void deleteProduto(ProdutoCesta produto) {
		this.produtos.remove(produto);
	}
	
	public float getValor() {
		float total = 0;
		
		for (ProdutoCesta produtoCesta : produtos) {
			total += produtoCesta.getValor();
		}
		
		return total;
	}
}
