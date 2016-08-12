package br.com.petshop.caokilate.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="consulta")
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private String descricao;
	private int idAnimal;
	
	public Consulta(){
		
	}
	public int getIdAnimal() {
		return idAnimal;
	}


	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}


	@Override
	public String toString() {
		return "Descricao "+getDescricao()+" ";
	}
	public Consulta(int cod, String descricao){
		setCodigo(cod);
		setDescricao(descricao);
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

	public String resumoDescricao(){
		return getDescricao().substring(0, 12) + "...";
	}
	


}
