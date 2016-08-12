package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.ordenadores.OrdenaServicoOrdeAlfabetica;

/**
 * Servlet implementation class ListaServicoAdm
 */
@WebServlet({ "/ListaServicoAdm", "/listaservicosadm" })
public class ListaServicoAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaServicoAdm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Servico> servicos = new Gerente().buscaTodosServico();
		Collections.sort(servicos, new OrdenaServicoOrdeAlfabetica());
		request.setAttribute("servicos",servicos);
		request.setAttribute("sucess", request.getAttribute("sucess"));
		request.getRequestDispatcher("listaservico.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
