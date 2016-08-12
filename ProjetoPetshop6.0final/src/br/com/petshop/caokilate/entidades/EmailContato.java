package br.com.petshop.caokilate.entidades;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.petshop.caokilate.exceptions.MsgInvalidException;


public class EmailContato {
	private String remetente;
	private String senha;
	private String assunto;
	private String msg;
	private String destino;
	private String hostName;
	
	public EmailContato(){
		setHostName("smtp.googlemail.com");
		setRemetente("petcaokilate@gmail.com");
		setSenha("petshop123");
	}
	public void send() throws MsgInvalidException{

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			//email.setSmtpPort(587);//pode deixar comentando pq procura uma porta padrao
			//email.setAuthenticator(new DefaultAuthenticator("username", "password"));
			email.setAuthentication("petcaokilate@gmail.com", "petshop123");
			email.setSSLOnConnect(true);
			email.setFrom("petcaokilate@gmail.com");
			email.setMsg(getMsg());
			email.setSubject(getAssunto());
			email.addTo("petcaokilate@gmail.com");
			email.send();
			enviaCopia();
			
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void enviaCopia() throws MsgInvalidException{
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			//email.setSmtpPort(587);//pode deixar comentando pq procura uma porta padrao
			//email.setAuthenticator(new DefaultAuthenticator("username", "password"));
			email.setAuthentication("petcaokilate@gmail.com", "petshop123");
			email.setSSLOnConnect(true);
			email.setFrom(getDestino());
			email.setMsg(copiaEmail(getMsg()));
			email.setSubject(getAssunto());
			email.addTo(getDestino());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			throw new MsgInvalidException();
		//	e.printStackTrace();
		}
		
		
	}
	public void recuperaSenha(){
		
		try {
			System.out.println("chegamos aqui");
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setAuthentication("petcaokilate@gmail.com", "petshop123");
			email.setSSLOnConnect(true);
			email.setFrom(getDestino());
			email.setMsg(getMsg());
			email.setSubject(getAssunto());
			email.addTo(getDestino());
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * smtp.live.com hotmail
	 * smtp.googlemail.com google
	 * smtp.myopera.com opera
	 * smtp.click21.com.br
	 * SMTP: smtp1.sympatico.ca
	 */
	private String copiaEmail(String msg){
		StringBuilder sb = new StringBuilder();
		sb.append(" Segue a copia de sua mensagem ao Petshop cao Kilate \n\n\n");
		sb.append(msg);
		sb.append("\n\n\n Petshop cao Kilate agrace o seu contato. \n Logo estaremos entrado em contato ");
		return sb.toString();
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	
}
