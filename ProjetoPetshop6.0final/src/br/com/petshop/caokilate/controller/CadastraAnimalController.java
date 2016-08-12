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

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;

/**
 * Servlet implementation class CadastraAnimalController
 */
@WebServlet({ "/CadastraAnimalController", "/cadastraanimal" })
public class CadastraAnimalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastraAnimalController() {
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
				//doGet(request, response);
		boolean atualizar = (request.getParameter("atualizar").equals("1") ? true : false);
				if(!atualizar)
					cadastraAnimal(request, response);
				else
					updateAnimal(request, response);
				
	}
	private void cadastraAnimal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<String> erros = Validacao.validaCadastroPet(request);
		
		if(!erros.isEmpty()){
			request.setAttribute("erros", erros);
			request.getRequestDispatcher("cadastroAnimal.jsp").forward(request, response);
		}else{
			Gerente gerente = new Gerente();
			HttpSession session = request.getSession();
			String login =(String) session.getAttribute("loginUsuario");
			if(login != null ){
				Usuario u = gerente.findUserByLogin(login);
				List<Animal> a = new ArrayList<Animal>();
				if(u.getAnimais() != null || !u.getAnimais().isEmpty())
					a.addAll(u.getAnimais());
				a.add(Fabrica.criaAnimal(request));
				u.setAnimais(a);
				gerente.atualizar(u);
				int fluxo = Integer.parseInt(request.getParameter("fluxo")); // define se vai pra apresentacao do servico ou consulta
				if(fluxo == 1){
					request.setAttribute("sucess", "Animal cadastrado com sucesso!");
					response.sendRedirect("buscaanimais");
					//request.getRequestDispatcher("buscaanimais").forward(request, response);
				}else if(fluxo == 3){
					request.setAttribute("sucess", "Animal cadastrado com sucesso!");
					request.getRequestDispatcher("minhaconta").forward(request, response);
				}
				else if(fluxo ==2){
					request.setAttribute("sucess", "Animal cadastrado com sucesso!");
					request.getRequestDispatcher("listaservicosdisponivel").forward(request, response);
				}
			}else{
				request.setAttribute("erros", erros.add("Voce precisa estar logado"));
				request.getRequestDispatcher("cadastroAnimal.jsp").forward(request, response);
			}
		}
	}
	/*private void updateAnimal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<String> erros = Validacao.validaCadastroPet(request);
		if(!erros.isEmpty()){
			request.setAttribute("erros", erros);
			request.getRequestDispatcher("alteraanimal.jsp");
		}else{
			Gerente gerente = new Gerente();
			Usuario user = gerente.findUserByLogin((String)request.getSession().getAttribute("loginUsuario"));
			List<Animal> animais = new ArrayList<>();
			int codigoAnimal = Integer.parseInt(request.getParameter("codigo"));
			Animal novoAnimal = Fabrica.criaAnimal(request);
			for (Animal animal : user.getAnimais()) {
					if(animal.getCodigo() != codigoAnimal)
						animais.add(animal);
			}
			novoAnimal.setCodigo(codigoAnimal);
			gerente.atualizar(novoAnimal);
			animais.add(novoAnimal);
			user.setAnimais(animais);
			response.sendRedirect("minhaconta");

			
		}*/
	private void updateAnimal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		List<String> erros = Validacao.validaCadastroPet(request);
		if(!erros.isEmpty()){
			request.setAttribute("erros", erros);
			request.getRequestDispatcher("alteraanimal.jsp");
		}else{
			Gerente gerente = new Gerente();
			int codigoAnimal = Integer.parseInt(request.getParameter("codigo"));
			Animal novoAnimal = Fabrica.criaAnimal(request);
			novoAnimal.setCodigo(codigoAnimal);
			gerente.atualizar(novoAnimal);
			request.setAttribute("sucess","Dados do animal alterado com sucesso!");
			request.getRequestDispatcher("minhaconta").forward(request, response);
		}
	}

}
