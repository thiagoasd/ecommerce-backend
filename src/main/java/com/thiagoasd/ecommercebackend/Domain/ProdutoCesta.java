package com.thiagoasd.ecommercebackend.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ProdutoCesta {

	@NotNull
	@ManyToOne(targetEntity = Produto.class)
	Produto produto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	
	@Min(0)
	int quantidade;
	

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getValor() {
		return this.quantidade * this.produto.getValor();
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean equals(ProdutoCesta produtoCesta) {
		return (this.quantidade == produtoCesta.getQuantidade()
			 && this.produto.equals(produtoCesta.getProduto()));
	
	}
	

}
