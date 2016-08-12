package br.com.petshop.caokilate.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String cep;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	
	public Endereco(){
		
	}
	public Endereco(String cep, String logradouro, int numero, String complemento, String bairro, String localidade, String uf){
		setCep(cep);
		setLogradouro(logradouro);
		setNumero(numero);
		setComplemento(complemento);
		setBairro(bairro);
		setLocalidade(localidade);
		setUf(uf);
	}
	
	@Override
	public String toString() {
		return "Cep "+getCep()+" Logradouro "+getLogradouro()+" Numero "+getNumero()+" Complemento "+getComplemento()
				+"\n Bairro "+getBairro()+" Localidade "+getLocalidade()+" Estado "+getUf();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
