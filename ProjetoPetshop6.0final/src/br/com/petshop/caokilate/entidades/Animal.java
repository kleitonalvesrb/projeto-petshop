package br.com.petshop.caokilate.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.petshop.caokilate.enumeradores.PorteAnimal;
import br.com.petshop.caokilate.enumeradores.TipoAnimal;

@Entity
public class Animal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 433354533431L;
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private TipoAnimal tipo;
	private String nome;
	private String raca;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private PorteAnimal porte;
	private double peso;
	
	
	
	public int getCodigo() {
		
		return codigo;
	}

	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	

	public TipoAnimal getTipo() {
		return tipo;
	}
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}
	public PorteAnimal getPorte() {
		return porte;
	}
	public void setPorte(PorteAnimal porte) {
		this.porte = porte;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}

	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Animal() {
		// TODO Auto-generated constructor stub
	}
	public Animal(int codigo, TipoAnimal tipo, String nome, String raca, PorteAnimal porte, double peso) {
		this.codigo=codigo;
		this.tipo=tipo;
		this.nome=nome;
		this.raca=raca;
		this.porte=porte;
		this.peso=peso;
	}
	
	@Override
	public String toString() {
		
		
		return "\n Tipo de animal: " + getTipo() +
		"\n Nome do animal: " + getNome() +
		"\n Raca: " + getRaca() + 
		"\n Porte:" + getPorte()+
		"\n Peso:" + getPeso();
	
	}
	
}
