package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class CriaServicoController
 */
@WebServlet({ "/CriaServicoController", "/criaservico" })
public class CriaServicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaServicoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		if(request.getSession().getAttribute("loginUsuario")!= null){
			boolean atualizar;
			atualizar = (request.getParameter("atualizar").equals("1") ? true : false);
			if(!atualizar)
				cadastraServico(request, response);
			else
				updateServico(request, response);
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
	private void cadastraServico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<String> erros = Validacao.validaCadastroServico(request);
		if(!erros.isEmpty()){
			request.setAttribute("erros", erros);
			request.getRequestDispatcher("cadastroServ.jsp").forward(request, response);;
		}else{
			request.setAttribute("sucess", "Servico cadastrado com sucesso!");
			new Gerente().persist(Fabrica.criaServico(request));
			request.getRequestDispatcher("listaservicosadm").forward(request, response);
		}
	
	}
	private void updateServico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<String> erros = Validacao.validaCadastroServico(request);
		if(!erros.isEmpty()){
			request.setAttribute("erros", erros);
			request.setAttribute("servico", new Gerente().findServicoByCod(Integer.parseInt(request.getParameter("id"))));
			request.getRequestDispatcher("alteraservico.jsp").forward(request, response);;
		}else{
			System.out.println(request.getParameter("id")+"<------- codigo");
			Servico servico = Fabrica.criaServico(request);
			servico.setCodigo(Integer.parseInt(request.getParameter("id")));
			new Gerente().atualizar(servico);
			request.setAttribute("sucess", "Servico alterado com sucesso!");
			request.getRequestDispatcher("listaservicosadm").forward(request, response);
		}
	}

}
