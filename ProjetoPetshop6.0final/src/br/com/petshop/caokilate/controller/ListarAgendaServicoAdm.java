package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.util.ComparaDataAgenda;

/**
 * Servlet implementation class ListarAgendaServicoAdm
 */
@WebServlet({ "/ListarAgendaServicoAdm", "/listaragendaservicoadm" })
public class ListarAgendaServicoAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarAgendaServicoAdm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gerente g = new Gerente();
		List<Agendamento> agendamentoServicos = new ArrayList<Agendamento>();
		if(request.getSession().getAttribute("loginUsuario")!=null){
			for(Agendamento agenda: g.buscarTodosAgendamento())
				if(agenda.getServico() != null)
					agendamentoServicos.add(agenda);
			Collections.sort(agendamentoServicos, new ComparaDataAgenda());
			request.setAttribute("agendaServicos", agendamentoServicos);
			request.setAttribute("sucess", request.getAttribute("sucess"));
			request.getRequestDispatcher("listar_agendaservicosadm.jsp").forward(request, response);
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
