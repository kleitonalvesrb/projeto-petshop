package br.com.petshop.caokilate.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Consulta;
import br.com.petshop.caokilate.entidades.Endereco;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;
import br.com.petshop.caokilate.enumeradores.PorteAnimal;
import br.com.petshop.caokilate.enumeradores.TipoAnimal;
import br.com.petshop.caokilate.enumeradores.TipoCompromisso;
import br.com.petshop.caokilate.enumeradores.UserType;

public class Fabrica {
	 /**
	   * Cria um usuario já tratando seus campos para que não tenha acentos e remoção de qualquer
	   * tipo de pontuação
	   * @param request
	   * @return novo usuario
	   */
		public static Usuario criaUsuario(HttpServletRequest request){
			Usuario u = new Usuario();
			u.setNome(Validacao.removeAccents(request.getParameter("nome")));
			u.setCpf(Validacao.trataString(request.getParameter("cpf")));
			u.setEmail(request.getParameter("email"));
			u.setFone(Validacao.trataString(request.getParameter("fone")));
			u.setLogin(Validacao.removeAccents(request.getParameter("login")));
			u.setSenha(Validacao.removeAccents(request.getParameter("senha")));
			u.setTipo(UserType.findTypeByValue(Integer.parseInt(request.getParameter("tipo"))));
			u.setEndereco(criaEndereco(request));
			System.out.println(u);
			return u;
		}
		/**
		 * Cria um endereço já tratando seus campos para que não tenha acentos e remoção de qualquer
		 * tipo de pontuação
		 * @param request
		 * @return
		 */
		public static Endereco criaEndereco(HttpServletRequest request){
			Endereco e = new Endereco();
			e.setCep(Validacao.trataString(request.getParameter("cep")));
			e.setBairro(Validacao.removeAccents(request.getParameter("bairro")));
			e.setComplemento(Validacao.removeAccents(request.getParameter("complemento")));
			e.setLogradouro(Validacao.removeAccents(request.getParameter("logradouro")));
			e.setLocalidade(Validacao.removeAccents(request.getParameter("cidade")));
			e.setUf(Validacao.removeAccents(request.getParameter("estado")));
			e.setNumero(Integer.parseInt(request.getParameter("numero")));
			return e;
		}
		public static Animal criaAnimal(HttpServletRequest request){
			Animal a = new Animal();
			a.setNome(request.getParameter("nome"));
			a.setPeso(Double.parseDouble(request.getParameter("peso")));
			a.setPorte(PorteAnimal.findByValue(Integer.parseInt(request.getParameter("porte"))));
			a.setTipo(TipoAnimal.findByValue(Integer.parseInt(request.getParameter("tipo"))));
			a.setRaca(request.getParameter("raca"));
			return a;
		}
		public static Consulta criaConstulta(HttpServletRequest request){
			Consulta c = new Consulta();
			c.setDescricao(request.getParameter("descricao"));
			c.setIdAnimal(Integer.parseInt(request.getParameter("animal")));
			
			return c;
		}
		public static Agendamento criaAgendamento(HttpServletRequest request){
			Agendamento a = new Agendamento();
			
			a.setBuscar(request.getParameter("taxiDog") != null ? true : false);
			a.setHora(Integer.parseInt(request.getParameter("horario")));
			String dataString = "yyyy-MM-dd"; 
			SimpleDateFormat spd = new SimpleDateFormat(dataString);
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String dataPega = request.getParameter("dataCadastro");
			try {
				Date date = (Date)formatter.parse(dataPega);
				a.setData(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			return a;
		}
		public static Usuario criaUsuarioAdm(HttpServletRequest request){
			Usuario u = new Usuario();
			u.setNome("Petshop cao Kilate");
			u.setCpf("00000000000");
			u.setEmail("petshop@gmail.com");
			u.setTipo(UserType.findTypeByValue(1));
			u.setLogin("adm");
			u.setSenha("adm123");
			u.setFone("6133911990");
			Endereco e = new Endereco();
			e.setBairro("Veredas");
			e.setLocalidade("Brasilia");
			e.setLogradouro("Quadra 03 bloco A");
			e.setComplemento("loja 6/7");
			e.setUf("DF");
			e.setCep("72755000");
			u.setEndereco(e);
			return u;
		}
		public static Servico criaServico(HttpServletRequest request){
			Servico servico = new Servico();
			servico.setDescricao(request.getParameter("descricao"));
			servico.setValor(Double.parseDouble(request.getParameter("valor")));
			servico.setDisponivel(request.getParameter("disponivel").equals("1") ? true : false);
			return servico;
		}
}