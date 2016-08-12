package br.com.petshop.caokilate.entidades;

import java.sql.Date;

public class Pedido {
	private int codigo;
	private Date data;
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}
	public Pedido(	int codigo,Date data) {
		setCodigo(codigo);
		setData(data);
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
@Override
public String toString() {
	return  "\n Data: " + getData() ;
}
}
