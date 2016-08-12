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
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class AgendaServicoController
 */
@WebServlet({ "/AgendaServicoController", "/agendaservico" })
public class AgendaServicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaServicoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = (String)request.getSession().getAttribute("loginUsuario");
		if(login != null){
			List<String> erros = Validacao.validaAgendamentoServico(request);
			if(!erros.isEmpty()){
				System.out.println("erro");
				System.out.println(erros);
				request.setAttribute("errosServicos", erros);
				//request.getSession().setAttribute("errosServico", erros);
				//request.getRequestDispatcher("servico.jsp").forward(request, response);;
				request.getRequestDispatcher("listaservicosdisponivel").forward(request, response);;
			}else{
				Gerente gerente = new Gerente();
				Usuario user = gerente.findUserByLogin(login);
				String op[] = request.getParameterValues("descricao");
				List<Agendamento> agendamentos = new ArrayList<>();
				agendamentos.addAll(user.getAgendamentos());
				Animal animal = gerente.findAnimalById(Integer.parseInt(request.getParameter("animal")));
				for(int i =0; i<op.length;i++){
					Servico ser = gerente.findServicoByCod(Integer.parseInt(op[i]));
					Agendamento a = Fabrica.criaAgendamento(request);
					a.setUsuario(user);
					a.setAnimal(animal);
					a.setServico(ser);
					agendamentos.add(a);
				}
				user.setAgendamentos(agendamentos);
				gerente.atualizar(user);
				request.setAttribute("sucess","Servico(s) agendado com sucesso! Aguarde confirmacao");
				request.getRequestDispatcher("index.jsp").forward(request, response);;
			}
		}
		/*else{
			/**
			 * falta terminar o agendamento, adc o campo idcompromisso na tabela agendamento para que 
			 * saiba qual é se é um servico ou consulta
			 *
			String []op = request.getParameterValues("descricao");
			Gerente g = new Gerente();
			for(int i = 0; i < op.length; i++){
				Agendamento agenda = Fabrica.criaAgendamento(request); 
				agenda.setCodigo(Integer.parseInt(op[i]));
				g.persist(agenda);
			}
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
