package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class ValidaLoginController
 */
@WebServlet({ "/ValidaLoginController", "/validalogin", "/validalogincontroller", "/login", "/entrar" })
public class ValidaLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Gerente g = new Gerente();
		Usuario u = g.findUserByLogin(request.getParameter("login"));
		if(u!=null){
			if(u.getSenha().equals(request.getParameter("senha"))){
				HttpSession session = request.getSession();
				session.setAttribute("nomeUsuario",u.getPrimeiroNome());
				session.setAttribute("loginUsuario", u.getLogin());
				session.setAttribute("tipo", u.getTipo());
				request.setAttribute("sucess", "Bem vindo "+u.getPrimeiroNome()+"!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				request.setAttribute("sucess", "Senha ou login informado errado!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("sucess", "Usuario informado nao esta cadastrado!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
			
		}
	
}
