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
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class AlteraServicoAgendaController
 */
@WebServlet({ "/AlteraServicoAgendaController", "/alteraservicoagenda" })
public class AlteraServicoAgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gerente gerente = new Gerente();
		Agendamento agendamentoServico = gerente.findAgendaByCod(Integer.parseInt(request.getParameter("codigo")));
		if(agendamentoServico != null){
			List<String> erros = Validacao.validaAgendamentoServico(request);
			if(!erros.isEmpty()){
				System.out.println(erros);
				request.setAttribute("errosServicos", erros);
				request.setAttribute("agendaservico", agendamentoServico);
				request.getRequestDispatcher("altera_servicoadm.jsp").forward(request, response);
				
			}else{
				Agendamento agenda = Fabrica.criaAgendamento(request);
				String op[] = request.getParameterValues("descricao");
				agenda.setCodigo(agendamentoServico.getCodigo());
				
				
				for(int i =0; i<op.length;i++){
					Servico ser = gerente.findServicoByCod(Integer.parseInt(op[i]));
					agenda = Fabrica.criaAgendamento(request);
					agenda.setUsuario(agendamentoServico.getUsuario());
					agenda.setAnimal(agendamentoServico.getAnimal());
					agenda.setServico(ser);
				}
				agenda.setCodigo(Integer.parseInt(request.getParameter("codigo")));
				agenda.setUsuario(agenda.getUsuario());
				gerente.atualizar(agenda);
				request.setAttribute("sucess", "Agendamento do servico alterado com sucesso! Contate o Cliente");
				request.getRequestDispatcher("listaragendaservicoadm").forward(request, response);
				
			}
		}else
			response.sendRedirect("index.jsp");
		System.out.println("Cheamos ate aqui");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
