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
import javax.websocket.SendResult;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;

/**
 * Servlet implementation class ListaServicoDisponivelController
 */
@WebServlet({ "/ListaServicoDisponivelController", "/listaservicosdisponivel" })
public class ListaServicoDisponivelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaServicoDisponivelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("errosServico", request.getParameter("errosServicos"));
		String login = (String)request.getSession().getAttribute("loginUsuario");
		if(login == null){
			response.sendRedirect("servico.jsp");
		}else{
			Gerente gerente = new Gerente(); 
			Usuario usuario = gerente.findUserByLogin(login); 
			List<Servico> servicos = gerente.buscaTodosServico();
			List<Servico> servicosDisponiveis = new ArrayList<>();
			for(Servico serv : servicos)
				if(serv.isDisponivel())
					servicosDisponiveis.add(serv);
			request.setAttribute("servicos", servicosDisponiveis);
			request.setAttribute("animais", usuario.getAnimais());
			request.setAttribute("erros", request.getAttribute("errosServicos"));
			request.setAttribute("sucess", request.getAttribute("sucess"));
			request.getRequestDispatcher("servico.jsp").forward(request, response);
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
