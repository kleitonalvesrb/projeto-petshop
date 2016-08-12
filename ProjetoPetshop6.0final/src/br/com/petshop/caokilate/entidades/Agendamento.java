package br.com.petshop.caokilate.entidades;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.petshop.caokilate.util.Util;
@Entity(name="agendamento")
@NamedQueries(
		@NamedQuery(name = "Agendamento.buscarTodos", query = "Select a from agendamento a")
	)
public class Agendamento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Servico servico;
	
	@Column(name = "horario")
	private int hora;
	
	@Column(name = "busca")
	private boolean isBuscar;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Animal animal;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Consulta consulta;
	@ManyToOne
	private Usuario usuario;
	public Agendamento() {
		// TODO Auto-generated constructor stub
	}
	public Agendamento(int codigo, Date data,int hora) {
		this.codigo= codigo;
		this.data= data;
		this.hora= hora;
	}
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public boolean isBuscar() {
		return isBuscar;
	}
	public void setBuscar(boolean isBuscar) {
		this.isBuscar = isBuscar;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return "\n Data do Agendamento: " + getData() +
				"\n Hora do Agendamento: " + getHora() ;
	}
	public String trataString(){
		return Util.dataExt(getData().toString());
	}
	
	public int dataInt(){
		return Util.convertDataInt(getData().toString());
	}
	public String dataFormAmericano(){
		return Util.dataFormAmericano(getData().toString());
	}
	
}
