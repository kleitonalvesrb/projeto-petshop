package br.com.petshop.caokilate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.util.Fabrica;
import br.com.petshop.caokilate.util.Validacao;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class ExcluiUsuarioController
 */
@WebServlet({ "/ExcluiUsuarioController", "/excluiusuario", "/excluianimal" })
public class ExcluiBeanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluiBeanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("tipoexclusao").equals("1")){
			try {
				excluiUsuario(request, response);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(request.getParameter("tipoexclusao").equals("2"))
			excluiAnimal(request,response);
		else if(request.getParameter("tipoexclusao").equals("4")){
			System.out.println("--------> exclusao servico");
			alteraDisponibilidade(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void excluiUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException{
		if(!Validacao.validaExclusao(request).isEmpty()){
			request.setAttribute("sucess", Validacao.validaExclusao(request).get(0).toString());
			request.getRequestDispatcher("minhaconta").forward(request, response);
		}else{
		String identificador = request.getParameter("id");
		Gerente gerente = new Gerente();
		Usuario user = gerente.findUserByLogin(request.getParameter("login"));
		Usuario adm = gerente.findUserByCpf("00000000000");
		String tipoUsuario = user.getTipo().toString();
		if(!tipoUsuario.equalsIgnoreCase("administrador")){
			
			if(user.getAgendamentos()  == null || user.getAgendamentos().isEmpty()){
				gerente.deleteUsuario(user);
			}else{
				for(Agendamento agenda: user.getAgendamentos())
					for(Animal animal : user.getAnimais())
						if(agenda.getAnimal().getCodigo() == animal.getCodigo())
							retiraAnimalUsuario(animal, agenda, adm, gerente);
						else{
							System.out.println("Teste");
							
						}
						
			}
			
			try {
				gerente.deleteUsuario(user);
				request.getSession().invalidate();
				response.sendRedirect("index.jsp");
			} catch (Exception e) {
				System.out.println("deu pau mais agr vai");
				excluiUsuario(request, response);
			}
			
			
		}else{
			request.setAttribute("dadosUsuario", user);
			request.setAttribute("sucess", "O administrador nao pode ser excluido");
			request.getRequestDispatcher("minhaconta.jsp").forward(request, response);
		}
		}
		
	}
	private void retiraAnimalUsuario(Animal animal, Agendamento agenda, Usuario adm, Gerente gerente){
		List<Animal> animais = adm.getAnimais();
		List<Agendamento> agendamentos = adm.getAgendamentos();
		animais.add(animal);
		agendamentos.add(agenda);
		for(Agendamento agendaAdm : agendamentos){
			agendaAdm.setUsuario(adm);
			System.out.println(agendaAdm+" -----------");
		}
		adm.setAgendamentos(agendamentos);
		gerente.atualizar(adm);
		adm.setAnimais(animais);
		gerente.atualizar(adm);
	}
	private void alteraDisponibilidade(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Gerente g = new Gerente();
		Servico s = g.findServicoByCod(Integer.parseInt(request.getParameter("id")));
		if(s!= null){
			s = Fabrica.criaServico(request);
			g.atualizar(s);
			response.sendRedirect("listaservicos");
		}
	}
	private void excluiAnimal(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Gerente gerente = new Gerente();
		Animal animal = gerente.findAnimalById(Integer.parseInt(request.getParameter("id")));
		if(animal != null){
			gerente.deleteUsuario(animal);
			response.sendRedirect("minhaconta");
		}else{
			System.out.println("animal nao encontrado");
			response.sendRedirect("index.jsp");
		}
		
	}

}
