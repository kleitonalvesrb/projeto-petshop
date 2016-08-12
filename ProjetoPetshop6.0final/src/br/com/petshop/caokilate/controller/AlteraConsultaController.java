package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.RequestContext;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Consulta;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class AlteraConsultaController
 */
@WebServlet({ "/AlteraConsultaController", "/alteraconsulta" })
public class AlteraConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraConsultaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gerente gerente = new Gerente();
		
		Agendamento agendamento = gerente.findAgendaByCod(Integer.parseInt(request.getParameter("codigo")));
		List<String> erros = Validacao.validaCadastroConsulta(request);
		if(!erros.isEmpty()){
			if(agendamento !=null){
				request.setAttribute("erros",erros);
				request.setAttribute("agenda", agendamento);
				request.getRequestDispatcher("altera_consultaadm.jsp").forward(request, response);
			}else{
				response.sendRedirect("listar_agendaconsultaadm");
			}
			
		}else{
			Agendamento agenda = Fabrica.criaAgendamento(request);
			Consulta consulta = Fabrica.criaConstulta(request);
			consulta.setCodigo(agendamento.getConsulta().getCodigo());// pra manter a mesma consulta e nao criar uma nova
			
			agenda.setUsuario(agendamento.getUsuario());
			agenda.setConsulta(consulta);
			agenda.setAnimal(agendamento.getAnimal());
			agenda.setCodigo(agendamento.getCodigo());
			gerente.atualizar(agenda);
			request.setAttribute("sucess", "Consulta alterada com sucesso! Contate o Cliente");
			request.getRequestDispatcher("listar_agendaconsultaadm").forward(request, response);
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
