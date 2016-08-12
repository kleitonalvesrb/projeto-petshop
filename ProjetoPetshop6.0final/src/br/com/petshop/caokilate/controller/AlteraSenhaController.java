package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class AlteraSenhaController
 */
@WebServlet({ "/AlteraSenhaController", "/alterasenha" })
public class AlteraSenhaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraSenhaController() {
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
		// TODO Auto-generated method stub
		String login =(String) request.getSession().getAttribute("loginUsuario");
		System.out.println("login -----> "+login);
		if(login != null){
			List<String> erros = Validacao.validaTrocaSenha(request, login);
			if(!erros.isEmpty()){
				request.setAttribute("erroAtualizacao", erros);
				request.setAttribute("dadosUsuario", new Gerente().findUserByLogin(login));
				request.getRequestDispatcher("minhaconta.jsp").forward(request, response);
			}else{
				Gerente g = new Gerente();
				Usuario user = g.findUserByLogin(login);
				user.setAnimais(g.findUserByLogin(login).getAnimais());
				user.setConsultas(g.findUserByLogin(login).getConsultas());
				user.setAgendamentos(g.findUserByLogin(login).getAgendamentos());
				user.getEndereco().setId(g.findUserByLogin(login).getEndereco().getId());
				user.setSenha(request.getParameter("novasenha"));
				g.atualizar(user);
				request.setAttribute("dadosUsuario", user);
				request.setAttribute("dadosAnimais", user.getAnimais());
				request.setAttribute("dadosAgendamento", user.getAgendamentos());
				request.setAttribute("sucess", "Senha alterada com sucesso");
				request.getRequestDispatcher("minhaconta.jsp").forward(request, response);
			}
		}else{
			System.out.println("erro!!!!!!!!!!!!!!!!!!");
			response.sendRedirect("index.jsp");
		}
	}

}
