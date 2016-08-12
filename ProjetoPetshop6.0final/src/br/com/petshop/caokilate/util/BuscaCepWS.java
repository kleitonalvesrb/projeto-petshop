package br.com.petshop.caokilate.util;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import br.com.petshop.caokilate.entidades.Endereco;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *72755243
 * @author kleitonalves
 */
public class BuscaCepWS {
 
	private static final String USER_AGENT = "Mozilla/5.0";
 
	public Endereco buscaEndereco(String cep){
		/*url que será passada para o webservice que faz o atendimento
		 * responde no formato json "cep" será o cep que irá ser pesquisado
		 * na base de dados do viacep.com.br*/
		String url = "http://viacep.com.br/ws/"+cep+"/json";
		try {
			
			String jsonRetorno = sendGet(url);/*Retorno do webservice*/
			Endereco endereco = new Endereco();
			Gson g = new Gson();
			java.lang.reflect.Type enderecoType = new TypeToken<Endereco>() {}.getType();
			endereco = g.fromJson(jsonRetorno, enderecoType);
			return endereco;
		} catch (Exception e) {
			System.out.println("erro na busca do cep");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	// HTTP GET request
	private static String sendGet(String url) throws Exception {
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		if(responseCode !=200){
			return "erro";
		}else if(responseCode == 404){
			JOptionPane.showMessageDialog(null, "sem acesso a internet");
			return "sem acesso";
		}
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();
 
	}
 
	// HTTP POST request
	private void sendPost() throws Exception {
 
		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
}
