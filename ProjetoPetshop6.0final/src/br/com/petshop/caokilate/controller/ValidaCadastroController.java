package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Endereco;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.enumeradores.UserType;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class ValidaCadastroController
 */
@WebServlet({ "/ValidaCadastroController", "/validacadastro" })
public class ValidaCadastroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gerente gerente = new Gerente();;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean atualizar;
		 atualizar = (request.getParameter("atualizar").equals("1") ? true : false);
		if(!atualizar)
			realizaCadastro(request,response);
		else
			realizaUpdateCadastro(request, response);
	}
	
	private void realizaUpdateCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(!Validacao.validaUsuario(request).isEmpty()){
			request.setAttribute("erroAtualizacao", Validacao.validaUsuario(request));
			request.setAttribute("dadosUsuario", getGerente().findUserByCpf(request.getParameter("cpf")));
			request.getRequestDispatcher("minhaconta.jsp").forward(request, response);

		}else{
			System.out.println("chegmoas aqui na atualizaçao");
			Usuario user = Fabrica.criaUsuario(request);
			user.setAnimais(getGerente().findUserByCpf(request.getParameter("cpf")).getAnimais());
			user.setConsultas(getGerente().findUserByCpf(request.getParameter("cpf")).getConsultas());
			user.setAgendamentos(getGerente().findUserByCpf(request.getParameter("cpf")).getAgendamentos());
			Endereco eNovo = Fabrica.criaEndereco(request);
			Endereco eAntigo = user.getEndereco();
			eNovo.setId(user.getEndereco().getId());
			user.setEndereco(eNovo);
		
			System.out.println("ENDERECO-- fabrica---> "+Fabrica.criaEndereco(request));
			
			
			getGerente().atualizar(user);
			request.getSession().setAttribute("nomeUsuario", user.getPrimeiroNome());
			request.setAttribute("sucess", "Conta atualizado com sucesso!");
			request.getRequestDispatcher("minhaconta").forward(request, response);
			//response.sendRedirect("minhaconta");
		}
	}
	
	private void realizaCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(!Validacao.validaUsuario(request).isEmpty()){
			request.setAttribute("erro", Validacao.validaUsuario(request));
			request.getRequestDispatcher("cadastro.jsp").forward(request, response);

		}else{
			getGerente().persist(Fabrica.criaUsuario(request));
			request.setAttribute("sucess", "Usuario cadastrado com sucesso!");/*msg de sucesso*/
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	
}
