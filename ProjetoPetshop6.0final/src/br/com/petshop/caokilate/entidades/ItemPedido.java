package br.com.petshop.caokilate.entidades;

public class ItemPedido {

	private int  codigo;
	private int qtd;
	private double valorTotal;
	private Pedido pedido;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public ItemPedido(int  codigo, int qtd, double valorTotal, Pedido pedido) {
	this.codigo = codigo;
	this.qtd = qtd;
	this.valorTotal = valorTotal;
	this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "\n Quantidade: " + getQtd() +
		"\n Valor Total: " + getValorTotal() +
		"\n Pedido: " + getPedido();
	}
}
