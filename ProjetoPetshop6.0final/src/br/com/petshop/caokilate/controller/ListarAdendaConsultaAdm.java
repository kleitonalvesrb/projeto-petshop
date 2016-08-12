package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Consulta;
import br.com.petshop.caokilate.util.ComparaDataAgenda;

/**
 * Servlet implementation class ListarAdendaConsultaAdm
 */
@WebServlet({ "/ListarAdendaConsultaAdm", "/listar_agendaconsultaadm" })
public class ListarAdendaConsultaAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarAdendaConsultaAdm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gerente g = new Gerente();
		List<Agendamento> agendaConsultas = new ArrayList<Agendamento>();
		if(request.getSession().getAttribute("loginUsuario")!= null){
			for(Agendamento agenda : g.buscarTodosAgendamento())
				if(agenda.getConsulta() != null)
					agendaConsultas.add(agenda);
			Collections.sort(agendaConsultas, new ComparaDataAgenda());
			request.setAttribute("agendaConsulta", agendaConsultas);
			request.setAttribute("sucess", request.getAttribute("sucess"));
			request.getRequestDispatcher("listar_agendaconsultasadm.jsp").forward(request, response);
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
