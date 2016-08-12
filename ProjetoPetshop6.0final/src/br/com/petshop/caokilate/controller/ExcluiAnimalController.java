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
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class ExcluiAnimalController
 */
@WebServlet({ "/ExcluiAnimalController", "/deleteanimal" })
public class ExcluiAnimalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcluiAnimalController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login =(String) request.getSession().getAttribute("loginUsuario");
		if(login != null){
			Integer codAnimal = Integer.parseInt(request.getParameter("id"));
			Gerente gerente = new Gerente();
			Animal animal = gerente.findAnimalById(codAnimal);
			if(gerente.buscaAgendaAnimal(codAnimal).isEmpty()){
				System.out.println("animal nao possui compromisso");
				removeAnimal(codAnimal, login, gerente);
				response.sendRedirect("minhaconta");
			}
			else{
				if(!autorizaExclusao(codAnimal)){
					request.setAttribute("sucess", "O animal nao pode ser removido, pois tem um servico/consulta a ser realizado!");
					request.getRequestDispatcher("minhaconta").forward(request, response);
					//request.getRequestDispatcher("minhaconta.jsp").forward(request, response);
				}else{
					removeAnimal(codAnimal, login, gerente);
					System.out.println("animal possui compromisso");
				}
			}
			
		}else
			response.sendRedirect("index.jsp");
		// TODO Auto-generated method stub
	}
	private void removeAnimal(int codigoAnimal, String login, Gerente g){
		Usuario u = g.findUserByLogin(login);
		List<Animal> animais = u.getAnimais();
		List<Animal> novosAnimais = new ArrayList<Animal>();
		for(Animal a :animais){
			if(a.getCodigo() != codigoAnimal){
				novosAnimais.add(a);
			}

		}
		u.setAnimais(novosAnimais);
		g.atualizar(u);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static boolean autorizaExclusao(int codAnimal){
		Gerente gerente = new Gerente();
		List<Agendamento> agenda = gerente.buscaAgendaAnimal(codAnimal);
		String dataAgenda =" ";
		for (Agendamento agendamento : agenda) {
			dataAgenda +=agendamento.dataInt();
			if(Validacao.isDataValida(dataAgenda))
				return false;
				
		}
		return true;
	}



}
