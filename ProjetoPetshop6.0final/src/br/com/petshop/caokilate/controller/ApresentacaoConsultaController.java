package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class ApresentacaoConsultaController
 */
@WebServlet({ "/ApresentacaoConsultaController", "/buscaanimais" })
public class ApresentacaoConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApresentacaoConsultaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sessio = request.getSession();
		System.out.println(sessio.getAttribute("loginUsuario")+"<-------------------------------------");
		String user = (String) sessio.getAttribute("loginUsuario");
		if(user != null){
			Usuario u = new Gerente().findUserByLogin(user);
			if(u.getAnimais() !=null)
				sessio.setAttribute("animais", u.getAnimais());
			else
				System.out.println("------> sem animais");
		}
		
		request.getRequestDispatcher("consulta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
