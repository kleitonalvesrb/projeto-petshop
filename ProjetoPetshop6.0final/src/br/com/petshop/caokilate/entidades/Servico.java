package br.com.petshop.caokilate.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
		@NamedQuery(name = "Servico.buscarTodos", query = "Select s from Servico s")
	)
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private String descricao;
	private double valor;
	@Column(name="disponivel")
	private boolean isDisponivel;
	public Servico() {
		// TODO Auto-generated constructor stub
	}
	public Servico(int codigo, String descricao, double valor) {
		this.codigo= codigo;
		this.descricao= descricao;
		this.valor= valor;
	}

	
	public boolean isDisponivel() {
		return isDisponivel;
	}
	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
@Override
public String toString() {
	return "\n Descricao do Servico: " + getDescricao() +
			"\n valor: " + getValor();
}
}
