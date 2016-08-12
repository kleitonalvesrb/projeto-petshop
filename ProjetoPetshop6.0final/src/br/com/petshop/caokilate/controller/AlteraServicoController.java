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
 * Servlet implementation class AlteraServicoController
 */
@WebServlet({ "/AlteraServicoController", "/alteraservico" })
public class AlteraServicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraServicoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loginUsuario") != null){
			List<String> erros = Validacao.validaCadastroServico(request);
			int codigoServicoAltrar = Integer.parseInt(request.getParameter("id"));
			Gerente gerente = new Gerente();
			Servico novoServico = gerente.findServicoByCod(codigoServicoAltrar);
			if(!erros.isEmpty()){
				request.setAttribute("erros", erros);
				request.setAttribute("servico", novoServico);
				request.getRequestDispatcher("alteraservico.jsp").forward(request, response);
			}else{
				
				novoServico = Fabrica.criaServico(request);
				novoServico.setCodigo(codigoServicoAltrar);
				gerente.atualizar(novoServico);
				request.setAttribute("sucess", "Servico alterado com sucesso!");
				request.getRequestDispatcher("listaservicosadm").forward(request, response);
			}
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

}
