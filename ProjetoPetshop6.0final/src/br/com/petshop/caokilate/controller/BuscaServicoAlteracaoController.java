package br.com.petshop.caokilate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Servico;

/**
 * Servlet implementation class BuscaServicoAlteracaoController
 */
@WebServlet({ "/BuscaServicoAlteracaoController", "/buscaservico" })
public class BuscaServicoAlteracaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaServicoAlteracaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//alterarservico.jsp
		Servico s =new Gerente().findServicoByCod(Integer.parseInt(request.getParameter("id")));
		if(s != null){
			request.setAttribute("servico", s);
			request.getRequestDispatcher("alteraservico.jsp").forward(request, response);
		}else{
			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
