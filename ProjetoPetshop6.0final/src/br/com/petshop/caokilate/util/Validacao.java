package br.com.petshop.caokilate.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.petshop.caokilate.dao.Gerente;
import br.com.petshop.caokilate.entidades.Usuario;

public class Validacao {
	/**
	 * Verifica se foi informado os campos do login
	 * @param request
	 * @return lista de erros
	 */
	public static List<String> validaParametrosLogin(HttpServletRequest request){
		List<String> erros = new ArrayList<>();
		if(request.getParameter("login").isEmpty())
			erros.add("O login deve ser informado");
		if(request.getParameter("senha").isEmpty())
			erros.add("A senha deve ser informada");
		return erros;
	}
	public static List<String> validaExclusao(HttpServletRequest request){
		List<String> erros = new ArrayList<>();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		if(login == null || senha == null)
			erros.add("Os campos de confirmacaoo devem ser preenchidos");
		else if(login.isEmpty() || senha.isEmpty()){
			erros.add("Os campos de confirmacao devem ser preenchidos");
		}else{
			Usuario user = new Gerente().findUserByLogin(login);
			if(user == null || user.getLogin().isEmpty())
				erros.add("Dados informado invalido");
			else{
				if(!user.getLogin().equals(login) || !user.getSenha().equals(senha))
					erros.add("Dados informado nao sao validos!");
			}
		}
		return erros;
	}
	/**
	 * Trata toda e qualquer string, retira os caracters
	 * ( ) . - / e espaco em branco; 
	 * @param str
	 * @return string tratada
	 */
	public static String trataString(String str){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < str.length(); i++)
			if(str.charAt(i)!='.' && str.charAt(i)!='/'
			&& str.charAt(i)!='(' && str.charAt(i)!=')'
			&& str.charAt(i)!='-' && str.charAt(i)!=' ')
				sb.append(str.charAt(i));
		return sb.toString();
	}
	/**
	 * Valida o endereço e informa os erros se existir
	 * @param request
	 * @return lista de erros
	 */
	public static List<String> validaUsuario(HttpServletRequest request){
		List<String> erros = new ArrayList<String>();
		/**
		 * Declaracao de atributos e remocao dos acentos
		 */
		String nome = removeAccents(request.getParameter("nome"));		
		String cpf = removeAccents(request.getParameter("cpf"));
		String tel = removeAccents(request.getParameter("fone"));
		String email = removeAccents(request.getParameter("email"));
		//String confiEmail = removeAccents(request.getParameter("confirmaEmail"));
		String login = removeAccents(request.getParameter("login"));
		String senha = removeAccents(request.getParameter("senha"));
		String congirmaSenha = removeAccents(request.getParameter("confirmaSenha"));

		/**
		 * validacoes dos dados 
		 */

		boolean atualizar = (request.getParameter("atualizar").equals("1") ? true : false);
		Gerente g = new Gerente();
		Usuario u = g.findUserByLogin(login);
		if(nome == null || nome.length() <3)
			erros.add("O campo nome deve ser devidamente preenchidos");
		if(!atualizar){
			if(cpf.isEmpty())
				erros.add("O campo CPF deve ser informado");
			else if(!validaCPF(cpf))
				erros.add("O cpf informado e invalido!");
			if(g.findUserByCpf(trataString(cpf))!=null)
				erros.add("Este cpf ja esta sendo utilizado por outro usuario");
		}
		System.out.println("erros ------> "+erros);
		
		if(trataString(tel).length() < 10 || trataString(tel).length() > 11)
			erros.add("O telefone deve ser informado corretamente Ex.: 6188991010");
		if(email.isEmpty())
			erros.add("O campo email deve ser informado");
		//		if(confiEmail.isEmpty())
		//			erros.add("O campo confirma email deve ser informado");
		//		if(!email.isEmpty() && !confiEmail.isEmpty())
		//			if(!email.equals(confiEmail))
		//				erros.add("O campo email e confirma email nao estao iguais");
		if(login == null || login.isEmpty()){
			erros.add("O campo login deve ser informado");
		}else if(login.length() < 3 || login.length() > 20)
			erros.add("O login deve ter entre 3 e 20 caracteres");
		if(!atualizar){
			if(u != null)
				erros.add("O usuario informado ja esta sendo utilizado");
		}
		if(senha.isEmpty())
			erros.add("O campo senha deve ser preenchido");
		if(congirmaSenha.isEmpty())
			erros.add("O campo confirma senha deve ser preenchido");
		if(!congirmaSenha.isEmpty() && !senha.isEmpty())
			if(senha.length() < 6 || senha.length()>16){
				erros.add("A senha deve ter entre 6 e 16 caracteres");
			}else if(!senha.equals(congirmaSenha))
				erros.add("O campo senha e confirma senha nao estao iguais");
		if(!validaEndereco(request).isEmpty())
			erros.addAll(validaEndereco(request));

		return erros;
	}
	/**
	 * Valida o endereço e informa os erros se existir
	 * @param request
	 * @return lista de erros
	 */
	public static List<String> validaEndereco(HttpServletRequest request){
		String cep = trataString(request.getParameter("cep"));
		String logradouro = removeAccents(request.getParameter("logradouro"));
		String estado = removeAccents(request.getParameter("estado"));
		String bairro = removeAccents(request.getParameter("bairro"));
		String complemento = removeAccents(request.getParameter("complemento"));
		List<String> erros = new ArrayList<String>();
		if(cep.isEmpty())
			erros.add("O campo CEP deve ser informado");
		else if(cep.length() !=8)
			erros.add("O campo Cep deve ser informado corretamente");
		if(logradouro.isEmpty())
			erros.add("O campo logradouro deve ser informado");
		if(estado.isEmpty())
			erros.add("O campo estado deve ser informado");
		if(bairro.isEmpty())
			erros.add("O campo bairro deve ser informado");
		if(!validaConversaoInt(request.getParameter("numero")))
			erros.add("O campo numero deve ser informado apenas por numeros inteiros.");
		else if(!validaInt(request.getParameter("numero")))
			erros.add("O campo numero nao pode ser negativo");

		return erros;

	}
	/**
	 * Valida se o numero informado é maior igual a zero
	 * @param inteiroString
	 * @return false <= 0 true >0
	 */
	private static boolean validaInt(String inteiroString){
		int numero = 0;
		if(validaConversaoInt(inteiroString))
			numero = Integer.parseInt(inteiroString);
		if(numero <= 0)
			return false;
		return true;
	}
	/**
	 * Verifica se a conversao de string para int seja possivel
	 * @param inteiroString
	 * @return
	 */
	private static boolean validaConversaoInt(String inteiroString){
		try {
			Integer.parseInt(inteiroString);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * Remove acentos de qualquer String
	 * @param str
	 * @return string sem acentuacao
	 */
	public static String removeAccents(String str) {
		if(str != null)
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		else
			return "";
	}
	/**
	 * Verifica se o cpf informado é valido, seguindo os criterios de calculo da RF
	 * @param cpfRecebido
	 * @return true/false
	 */
	private static boolean validaCPF(String cpfRecebido) { 
		String []cpfInvalidos = {"00000000000","11111111111","22222222222","33333333333",
								 "44444444444","55555555555", "66666666666","77777777777",
								 "88888888888","99999999999"};
	
		String cpf = trataString(cpfRecebido);
		boolean ret = false;  
		if(cpf == null || cpf.isEmpty())
			return ret;
		for(int i = 0; i < cpfInvalidos.length; i++){
			if(cpfInvalidos[i].equals(cpf)){
				System.out.println("CPF INFORMADO "+cpfRecebido+"CPF INVALIDO "+cpfInvalidos[i]);
				return false;
			}else
				System.out.println("ELSE CPF INFORMADO "+cpfRecebido+"CPF INVALIDO "+cpfInvalidos[i]);
		}
		String base = "000000000";  
		String digitos = "00";  
		if (cpf.length() <= 11) {  
			if (cpf.length() < 11) {  
				cpf = base.substring(0, 11 - cpf.length()) + cpf;  
				base = cpf.substring(0, 9);  
			}  

			base = cpf.substring(0, 9);  
			digitos = cpf.substring(9, 11);  
			int soma = 0, mult = 11;  
			int[] var = new int[11];  
			// Recebe os números e realiza a multiplicação e soma.  
			for (int i = 0; i < 9; i++) {  
				var[i] = Integer.parseInt("" + cpf.charAt(i));  
				if (i < 9)  
					soma += (var[i] * --mult);  
			}  
			// Cria o primeiro dígito verificador.  
			int resto = soma % 11;  
			if (resto < 2) {  
				var[9] = 0;  
			} else {  
				var[9] = 11 - resto;  
			}  
			// Reinicia os valores.  
			soma = 0;  
			mult = 11;  
			// Realiza a multiplicação e soma do segundo dígito.  
			for (int i = 0; i < 10; i++)  
				soma += var[i] * mult--;  
			// Cria o segundo dígito verificador.  
			resto = soma % 11;  
			if (resto < 2) {  
				var[10] = 0;  
			} else {  
				var[10] = 11 - resto;  
			}  
			if ((digitos.substring(0, 1).equalsIgnoreCase(new Integer(var[9])  
					.toString()))  
					&& (digitos.substring(1, 2).equalsIgnoreCase(new Integer(  
							var[10]).toString()))) {  
				ret = true;  
			}  
		}

		return ret;  
	}
	public static List<String> validaCadastroConsulta(HttpServletRequest request){
		List<String> erros = new ArrayList<String>();
		if(request.getParameter("dataCadastro").isEmpty())
			erros.add("A data deve ser informada");
		else if(!isDataValida(request.getParameter("dataCadastro")))
			erros.add("A data deve ser no futuro");
		if(request.getParameter("horario").isEmpty())
			erros.add("O horario da consulta deve ser informado");
		if(request.getParameter("animal") == null || request.getParameter("animal").isEmpty())
			erros.add("O animal deve ser informado");
		if(request.getParameter("descricao").isEmpty())
			erros.add("A descricao do problema do animal deve ser informado");
		else if(request.getParameter("descricao").length() < 15)
			erros.add("A descricao do problema do animal deve ser bem detalhada");

		return erros;
	}
	/*public static boolean isDataValida(String dataInformada){
  		Date data = new Date();  
  		String dataString = "yyyy-MM-dd";  
  		SimpleDateFormat spd = new SimpleDateFormat(dataString);
  		String dataAtual = spd.format(data);

  		if(dataInformada.equals(dataAtual))
  			return false;
  		return true;
  	}*/
	public static boolean isDataValida(String dataIndormada){
		Date data = new Date();
		String dataString = "yyyy-MM-dd";
		SimpleDateFormat spd = new SimpleDateFormat(dataString);
		String dataAtual = spd.format(data);
		int dataAtualInt = 0;
		int dataInformadaInt = 0;
		try {
			dataAtualInt = Integer.parseInt(trataString(dataAtual));
			dataInformadaInt = Integer.parseInt(trataString(dataIndormada));
			if(dataInformadaInt - dataAtualInt >0)
				return true;
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return false;
	}
	public static List<String> validaCadastroPet(HttpServletRequest request){
		List<String> erros = new ArrayList<>();
		if(request.getParameter("nome").isEmpty())
			erros.add("O nome do animal deve ser informado");
		if(request.getParameter("tipo").isEmpty())
			erros.add("O tipo do animal deve ser informado");
		if(request.getParameter("peso").isEmpty()){
			erros.add("O campo peso deve ser informado");
		}else{
			if(!validaFloat(request.getParameter("peso")))
				erros.add("o peso deve ser informado somente com numeros");
			if(!validaLimite(request.getParameter("peso"),0.100,5000))
				erros.add("O peso informado nao obedece ao valor minimo de "+0.100+" e maximo de "+5000);
		}
		if(request.getParameter("porte").isEmpty())
			erros.add("O porte do animal deve ser informado");
		return erros;
	}
	public static List<String> validaCadastroServico(HttpServletRequest request){
		List<String> erros = new ArrayList<>();
		if(request.getParameter("descricao").isEmpty())
			erros.add("O campo descricao deve ser preenchido");
		else if(request.getParameter("descricao").length() <3)
			erros.add("A descricao deve ter mais de 3 letras");
		if(request.getParameter("valor").isEmpty())
			erros.add("O campo valor deve ser informado");
		else {
			if(!validaFloat(request.getParameter("valor")))
				erros.add("O campo valor deve ser informado apenas com numeros");
			else if(!validaLimite(request.getParameter("valor"), 10, 5000))
				erros.add("O valor do servico nao obedece ao valor minimo de "+10+" e maximo de "+5000);
		}
		if(request.getParameter("disponivel") == null || request.getParameter("disponivel").isEmpty())
			erros.add("A disponibilidade do servico deve ser informada");
		return erros;

	}
	public static List<String> validaAgendamentoServico(HttpServletRequest request){
		List<String> erros = new ArrayList<>();
		String op[] = request.getParameterValues("descricao");
		if(request.getParameter("animal") == null || request.getParameter("animal").isEmpty())
			erros.add("O animal deve ser informado");
		if(op == null || op.length == 0)
			erros.add("Pelo menos um servico deve ser informado");
		if(request.getParameter("dataCadastro") == null || request.getParameter("dataCadastro").isEmpty())
			erros.add("A data deve ser informada");
		else if(!isDataValida(request.getParameter("dataCadastro")))
			erros.add("A data informada deve ser no futuro");
		if(request.getParameter("horario") == null || request.getParameter("horario").isEmpty())
			erros.add("O horario deve ser informado");
		return erros;
	}
	public static List<String> validaTrocaSenha(HttpServletRequest request, String login){
		String senhaAtual = request.getParameter("atualsenha");
		String novasenha = request.getParameter("novasenha");
		String confirmaSenha = request.getParameter("confnovasenha");
		System.out.println("Senha Atual "+senhaAtual);
		System.out.println("Nova senha "+novasenha+" confirmacao da nova senha "+confirmaSenha);
		System.out.println("Usuario "+login);
		//		
		Usuario user = new Gerente().findUserByLogin(login);
		System.out.println("user ---> "+user.getSenha()+"<-----");
		System.out.println();
		List<String> erros = new ArrayList<>();
		if(senhaAtual.isEmpty())
			erros.add("A senha atual deve ser informada");
		else if(!senhaAtual.equals(user.getSenha()))
			erros.add("A senha atual não confere");
		else if(novasenha.length() < 6 || novasenha.length() > 16)
			erros.add("A nova senha deve ter entre 6 e 16 letras");
		else if(novasenha.equals(senhaAtual))
			erros.add("Para realizar a troca de senha, deve ser informado uma nova senha");
		else if(!novasenha.equals(confirmaSenha))
			erros.add("A confirmação nao e igual a senha informada");
		return erros;
	}
	public static boolean validaFloat(String numString){
		try {
			Double.parseDouble(numString);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static boolean validaLimite(String numString, double min, double max){
		if(validaFloat(numString)){
			double valor = Double.parseDouble(numString);
			if(valor < min || valor > max)
				return false;
		}
		return true;
	}
	/*
	 * codigo pra apresentar de forma correta a data que é pega no banco
	 * 
	 */
	public static boolean validaRecuperacaoSenha(HttpServletRequest request){
		String login = request.getParameter("login");
		String email = request.getParameter("login");
		if(login.isEmpty() || email.isEmpty())
			return false;
		return true;
	}
	public static boolean validaRecuperacaoLogin(HttpServletRequest requset){
		String cpf = requset.getParameter("cpf");
		String email = requset.getParameter("email");
		if(cpf.isEmpty() || email.isEmpty())
			return false;
		return true;
	}

}
