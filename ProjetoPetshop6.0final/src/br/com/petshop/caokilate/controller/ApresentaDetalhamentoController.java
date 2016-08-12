package br.com.petshop.caokilate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Consulta;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class ApresentaDetalhamentoController
 */
@WebServlet({ "/ApresentaDetalhamentoController", "/detalhamento" })
public class ApresentaDetalhamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApresentaDetalhamentoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int codigo = Integer.parseInt(request.getParameter("id_agenda"));
		Gerente g = new Gerente();
		Agendamento agenda = g.findAgendaByCod(codigo);
		System.out.println(agenda);
		if(agenda.getConsulta() != null){
			Animal a = g.findAnimalById(agenda.getConsulta().getIdAnimal());
			if(a!=null)
				request.setAttribute("animal",a);
			request.setAttribute("consulta",agenda.getConsulta());
			request.setAttribute("agenda", agenda);
			request.getRequestDispatcher("detalhaconsulta.jsp").forward(request, response);
		}else{
			Servico servico = g.findServicoByCod(agenda.getServico().getCodigo());
			if(servico != null)
				request.setAttribute("servico", servico);
			request.setAttribute("agenda", agenda);
			request.getRequestDispatcher("detalhaservico.jsp").forward(request, response);;
		}
		
		
		/*Usuario user = new Gerente().findUserByLogin((String)request.getSession().getAttribute("loginUsuario"));
		if(user != null){
			Consulta consultaDetalhar = new Consulta();
			for(Consulta consulta : user.getConsultas())
				if(consulta.getCodigo() == codigo)
					consultaDetalhar = consulta;
			for(Animal animal: user.getAnimais())
				if(animal.getCodigo() == consultaDetalhar.getIdAnimal())
					request.setAttribute("animal", animal);
			request.setAttribute("consulta",consultaDetalhar);
			request.getRequestDispatcher("detalhaconsulta.jsp").forward(request, response);;
		}else{
			response.sendRedirect("minhaconta");
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
