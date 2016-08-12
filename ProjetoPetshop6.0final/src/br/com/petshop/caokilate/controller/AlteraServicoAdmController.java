package br.com.petshop.caokilate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;

/**
 * Servlet implementation class AlteraServicoAdmController
 */
@WebServlet({ "/AlteraServicoAdmController", "/alteraservicoadm" })
public class AlteraServicoAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loginUsuario") != null){
			Gerente gerente = new Gerente();
			Agendamento agendaServico = gerente.findAgendaByCod(Integer.parseInt(request.getParameter("codigoServico")));
			if(agendaServico != null){
				request.setAttribute("servicos", gerente.buscaTodosServico());
				request.setAttribute("agendaservico", agendaServico);
				request.getRequestDispatcher("altera_servicoadm.jsp").forward(request, response);;
			}else
				response.sendRedirect("index.jsp");
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
