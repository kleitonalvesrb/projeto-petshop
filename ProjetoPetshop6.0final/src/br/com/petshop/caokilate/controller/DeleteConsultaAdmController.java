package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class DeleteConsultaAdmController
 */
@WebServlet({ "/DeleteConsultaAdmController", "/deleteconsultaadm" })
public class DeleteConsultaAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteConsultaAdmController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loginUsuario") != null){
			Agendamento agendamento = new Agendamento();
			Gerente gerente = new Gerente();
			Usuario user = agendamento.getUsuario();
			agendamento = gerente.findAgendaByCod(Integer.parseInt(request.getParameter("codigoAgenda")));
			gerente.deleteUsuario(agendamento);
			List<Agendamento> agendaAposExclusao = gerente.buscaAgendaCliente(agendamento.getUsuario().getCpf());
			System.out.println("Agendamento ---->"+agendaAposExclusao+" Tamanho "+agendaAposExclusao.size());
			System.out.println("usuario ---->"+agendamento.getUsuario());
			agendamento.getUsuario().setAgendamentos(agendaAposExclusao);
			gerente.atualizar(agendamento.getUsuario());
			request.setAttribute("sucess", "Consulta excluida com sucesso! Contate ao Cliente");
			request.getRequestDispatcher("listar_agendaconsultaadm").forward(request, response);
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
