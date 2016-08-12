package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Endereco;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.enumeradores.UserType;
import br.com.petshop.caokilate.util.Fabrica;

/**
 * Servlet implementation class CriaAdmController
 */
@WebServlet({ "/CriaAdmController", "/criaadmcontroller", "/criaadm" })
public class CriaAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario u = Fabrica.criaUsuarioAdm(request);
		Gerente gerente = new Gerente();
		if(gerente.findUserByCpf(u.getCpf()) == null && gerente.findUserByLogin(u.getLogin()) == null)
			gerente.persist(u);
		response.sendRedirect("index.jsp");
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	


}
