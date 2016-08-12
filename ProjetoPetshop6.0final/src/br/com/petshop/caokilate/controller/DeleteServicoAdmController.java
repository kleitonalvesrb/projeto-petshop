package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class DeleteServicoAdmController
 */
@WebServlet({ "/DeleteServicoAdmController", "/deleteservicoadm" })
public class DeleteServicoAdmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServicoAdmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("loginUsuario") != null){
			Gerente g = new Gerente();
			int codigoAgenda = Integer.parseInt(request.getParameter("codigoServico"));
			/*Agendamento agenda =  g.findAgendaByCod(codigoAgenda);// pegamos a agenda
			Usuario user = agenda.getUsuario();
			//Servico servicoExistente = agenda.getServico();// guardamos o servico existente
			agenda.setServico(criaServicoAux());
			g.atualizar(agenda);
			//System.out.println(" ------>"+agenda);
			
			user.setEndereco(user.getEndereco());
			user.setAnimais(user.getAnimais());
			List<Agendamento> agendamentoUsuario = user.getAgendamentos();
			List<Agendamento> novaAgenda = new ArrayList<Agendamento>();
			for(Agendamento agendamento : agendamentoUsuario)
				if(agendamento.getCodigo() == codigoAgenda)
					novaAgenda.add(agendamento);
			user.setAgendamentos(novaAgenda);
			g.atualizar(user);
			
			g.deleteUsuario(agenda);//deletamos a agenda juntamente com o servico auxiliar
			request.setAttribute("sucess", "Agendamento de serviço excluido com sucesso! Contate o Cliente");
			request.getRequestDispatcher("index.jsp").forward(request, response);*/
			delecaoAlternativa(codigoAgenda, request, response);
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
	private void delecaoAlternativa(int codigo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Gerente g = new Gerente();
		Agendamento agenda = g.findAgendaByCod(codigo);
		agenda.setServico(criaServicoAux());
		
		g.atualizar(agenda);
		g.deleteUsuario(agenda);
		Usuario user = agenda.getUsuario();
		List<Agendamento> agendaAposDelecao = g.buscaAgendaCliente(agenda.getUsuario().getCpf());
		user.setAgendamentos(agendaAposDelecao);
		g.atualizar(user);
		System.out.println("------->"+agendaAposDelecao +" Qunatidade "+agendaAposDelecao.size());

		
		
		request.setAttribute("sucess", "Agendamento de servico excluido com sucesso! Contate o Cliente");
		request.getRequestDispatcher("listaragendaservicoadm").forward(request, response);
	}
	private Servico criaServicoAux(){
		Servico ser = new Servico();
		ser.setDescricao("eh apenas um auxiliar");
		ser.setDisponivel(false);
		ser.setValor(0);
		new Gerente().persist(ser);
		return ser;
	}

}
