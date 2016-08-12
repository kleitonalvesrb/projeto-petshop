package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import br.com.petshop.caokilate.entidades.Consulta;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class AgendarConslutaController
 */
@WebServlet({ "/AgendarConslutaController", "/agendaconsulta" })
public class AgendarConslutaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendarConslutaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Gerente gerente = new Gerente();
		String login =(String) session.getAttribute("loginUsuario");
		if(login != null && !login.isEmpty()){
			Usuario u = gerente.findUserByLogin(login);
			List<Agendamento> agendamentos = new ArrayList<Agendamento>();
			agendamentos.addAll(u.getAgendamentos());
			Agendamento agenda = new Agendamento();
			
			Consulta consulta = new Consulta();
			List<String> erros = Validacao.validaCadastroConsulta(request);
			if(!erros.isEmpty()){
				request.setAttribute("erros", erros);
				request.getRequestDispatcher("consulta.jsp").forward(request, response);
			}else{
				Animal animal = gerente.findAnimalById(Integer.parseInt(request.getParameter("animal")));// busca no banco o animal escolhido no select
				agenda = Fabrica.criaAgendamento(request);// cria uma agenda
				
				consulta = Fabrica.criaConstulta(request); // cria uma consulta
				
				agenda.setAnimal(animal); // coloca o animal escolhido dentro da agenda
				
				agenda.setConsulta(consulta);// coloca na agenda a consulta marcada
				
				agenda.setUsuario(u);
				agendamentos.add(agenda);
				u.setAgendamentos(agendamentos);
				gerente.atualizar(u);
				request.setAttribute("sucess", "Consulta cadastrada com sucesso! Aguarde o email de confirmacao");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else{
			response.sendRedirect("buscaanimais");
		}

	}
}
