package br.com.petshop.caokilate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.petshop.caokilate.entidades.EmailContato;
import br.com.petshop.caokilate.exceptions.MsgInvalidException;

/**
 * Servlet implementation class ContatoController
 */
@WebServlet({ "/ContatoController", "/contatocontroller" })
public class ContatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assunto = request.getParameter("assunto");
		String emailEn = request.getParameter("email");
		String msg = request.getParameter("comentario");
		EmailContato emailContato = new EmailContato();
		emailContato.setAssunto(assunto);
		emailContato.setMsg(msg);
		emailContato.setDestino(emailEn);
		try {
			emailContato.send();
		} catch (MsgInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		response.sendRedirect("index.jsp");
	}

}
