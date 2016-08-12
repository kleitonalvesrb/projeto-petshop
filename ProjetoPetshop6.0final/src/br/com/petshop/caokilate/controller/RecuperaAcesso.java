package br.com.petshop.caokilate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.Email;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.EmailContato;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class RecuperaAcesso
 */
@WebServlet({ "/RecuperaAcesso", "/recuperaacesso" })
public class RecuperaAcesso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperaAcesso() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer tipo = Integer.parseInt(request.getParameter("tipo"));
		if(tipo == 1){
			recuperaSenha(request, response);
		}else{
			recuperaLogin(request, response);
		}
	}
	private void recuperaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(!Validacao.validaRecuperacaoLogin(request)){
			request.setAttribute("sucess", "O preenchimento dos campos e obrigatorio!");
			request.getRequestDispatcher("esqueci.jsp").forward(request, response);
		}else{
			Usuario user = new Gerente().findUserByCpf(Validacao.trataString(request.getParameter("cpf")));
			if(user == null || user.getCpf().isEmpty()){
				request.setAttribute("sucess", "O cpf informado nao foi encontrado!");
				request.getRequestDispatcher("esqueci.jsp").forward(request, response);
			}else{
				if(user.getEmail().equals(request.getParameter("email"))){
					EmailContato recuperaEmail = new  EmailContato();
					recuperaEmail.setAssunto("Recuperacao de login");
					recuperaEmail.setMsg("Esta e uma mensagem automatica \n"
							+ "Voce solicitou a recuperacao de login no petshop CaoKilate \n"
							+ "Sua senha: "+user.getSenha()+"\n "
							+ "Seu usuario: "+user.getLogin());
					recuperaEmail.setDestino(user.getEmail());
					recuperaEmail.recuperaSenha();
					request.setAttribute("sucess", "Um email com seu login e senha foram enviados! Verifique a sua caixa de spam!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					request.setAttribute("sucess", "O email informado nao corresponde!");
					request.getRequestDispatcher("esqueci.jsp").forward(request, response);
				}
			}
		}
	}
	private void recuperaSenha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(!Validacao.validaRecuperacaoSenha(request)){
			request.setAttribute("sucess", "O preenchimento dos campos sao obrigatorio!");
			request.getRequestDispatcher("esqueci.jsp").forward(request, response);
		}else{
			Usuario user = new Gerente().findUserByLogin(request.getParameter("login"));
			if(user == null || user.getLogin().isEmpty()){
				request.setAttribute("sucess", "O usuario informado nao foi encontrado!");
				request.getRequestDispatcher("esqueci.jsp").forward(request, response);
			}else{
				if(user.getEmail().equals(request.getParameter("email"))){
					EmailContato recuperaEmail = new  EmailContato();
					recuperaEmail.setAssunto("Recuperacao de senha");
					recuperaEmail.setMsg("Esta e uma mensagem automatica \n"
							+ "Voce solicitou a recuperacao de senha no petshop CaoKilate \n"
							+ "Sua senha: "+user.getSenha()+"\n "
							+ "Seu usuario: "+user.getLogin());
					recuperaEmail.setDestino(user.getEmail());
					recuperaEmail.recuperaSenha();
					request.setAttribute("sucess", "Um email com sua senha foi enviada! Verifique a sua caixa de spam!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else{
					request.setAttribute("sucess", "O email informado nao corresponde!");
					request.getRequestDispatcher("esqueci.jsp").forward(request, response);
				}
			}
		}
	}
}
