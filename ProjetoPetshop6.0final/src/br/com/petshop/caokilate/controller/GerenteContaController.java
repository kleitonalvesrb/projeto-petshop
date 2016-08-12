package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.ComparaDataAgenda;

/**
 * Servlet implementation class GerenteContaController
 */
@WebServlet({ "/GerenteContaController", "/minhaconta" })
public class GerenteContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GerenteContaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("loginUsuario");
		if(login != null && !login.isEmpty()){
			if(request.getAttribute("sucess")!=null)
				request.setAttribute("sucess", request.getAttribute("sucess"));
			Usuario usuarioApresentacao = new Gerente().findUserByLogin(login);
			request.setAttribute("dadosUsuario", usuarioApresentacao);
			List<Animal> animais = usuarioApresentacao.getAnimais();
			request.setAttribute("dadosAnimais",usuarioApresentacao.getAnimais());
			Collections.sort(usuarioApresentacao.getAgendamentos(),  new ComparaDataAgenda());
			for(Agendamento a : usuarioApresentacao.getAgendamentos()){
				System.out.println(a.getConsulta());
				System.out.println(a.getServico());
			}
			request.setAttribute("sucess", request.getAttribute("sucess"));
			request.setAttribute("dadosAgendamento", usuarioApresentacao.getAgendamentos());
			request.getRequestDispatcher("minhaconta.jsp").forward(request, response);
		}else
			response.sendRedirect("index.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
