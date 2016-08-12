package br.com.petshop.caokilate.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private String nome;
	private double preco;
	private int qtd;

	
public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
public Produto() {
	// TODO Auto-generated constructor stub
}
public Produto(int codigo, String nome, double preco, int qtd) {
	this.codigo= codigo;
	this.nome= nome;
	this.preco= preco;
	this.qtd= qtd;
}
@Override
	public String toString() {
	return "Nome do Produto: " + getNome() +
	"Preco: " + getPreco() +
	"Quantidade: " + getQtd();
		
	}

}
