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
	Apenas pega a consulta e manda pra pagina jsp para realizar a alteraçao da consulta
 */
@WebServlet({ "/AlteraConsultaAdmController", "/alteraconsultaadm" })
public class AlteraConsultaAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraConsultaAdmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loginUsuario") != null){
			Gerente gerente = new Gerente();
			Agendamento agenda = gerente.findAgendaByCod(Integer.parseInt(request.getParameter("codigoAgenda")));
			if(agenda != null){
				request.setAttribute("agenda",agenda);
				request.getRequestDispatcher("altera_consultaadm.jsp").forward(request, response);
			}else
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
