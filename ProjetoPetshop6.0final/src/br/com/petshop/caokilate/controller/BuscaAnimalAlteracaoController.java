package br.com.petshop.caokilate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Fabrica;

/**
 * Servlet implementation class BuscaAnimalAlteracaoController
 */
@WebServlet({ "/BuscaAnimalAlteracaoController", "/alteraanimal" })
public class BuscaAnimalAlteracaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaAnimalAlteracaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigoAnimal = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Gerente gerente = new Gerente();
		Usuario user = gerente.findUserByLogin((String)session.getAttribute("loginUsuario"));
		if(user != null){
		for (Animal animal : user.getAnimais()) 
			if(animal.getCodigo() == codigoAnimal){
				request.setAttribute("animal", animal);
			}
		}
		request.setAttribute("sucess", request.getAttribute("sucess"));
		request.getRequestDispatcher("alteraanimal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
