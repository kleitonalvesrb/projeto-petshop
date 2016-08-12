package br.com.petshop.caokilate.util;

public class Util {
	public static int convertDataInt(String data){
		int dataInt =0;
		StringBuilder dia = new StringBuilder();
		StringBuilder mes = new StringBuilder();
		StringBuilder ano = new StringBuilder();
		StringBuilder dataCompleta = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			if(i>=8 && i<=9)
				dia.append(data.charAt(i));
			if(i >3 && i<=6)
				mes.append(data.charAt(i));
			if(i>=25 && i<=29)
				ano.append(data.charAt(i));
		}
		dataCompleta.append(ano);
		dataCompleta.append(convertMesToInt(mes.toString()));
		dataCompleta.append(dia);
		String datacompleta = dataCompleta.toString();
		try {
			dataInt = Integer.parseInt(datacompleta);
		} catch (NumberFormatException e) {
			System.out.println("erro na comparacao!");
		}
		
		return dataInt;
	}
	
	
	public static int convertMesToInt(String mesString){
		switch (mesString) {
		case "Jan":
			return 01;
		case "Feb":
			return 02;
		case "Mar":
			return 03;
		case "Apr":
			return 04;
		case "May":
			return 05;
		case "Jun":
			return 06;
		case "Jul":
			return 07;
		case "Aug":
			return 8;
		case "Sep":
			return 9;
		case "Oct":
			return 10;
		case "Nov":
			return 11;
		case "Dec":
			return 12;
		default:
			return 0;
		}
	}
	public static String dataFormAmericano(String data){
		StringBuilder dia = new StringBuilder();
		StringBuilder mes = new StringBuilder();
		StringBuilder ano = new StringBuilder();
		StringBuilder dataCompleta = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			if(i>=8 && i<=9)
				dia.append(data.charAt(i));
			if(i >3 && i<=6)
				mes.append(data.charAt(i));
			if(i>=25 && i<=29)
				ano.append(data.charAt(i));
		}
		dataCompleta.append(ano);
		dataCompleta.append("-");
		dataCompleta.append(convertMesToInt(mes.toString()));
		dataCompleta.append("-");
		dataCompleta.append(dia);
		
		return dataCompleta.toString();
	}
	public static String dataExt(String data){
		StringBuilder dia = new StringBuilder();
		StringBuilder mes = new StringBuilder();
		StringBuilder ano = new StringBuilder();
		StringBuilder dataCompleta = new StringBuilder();
		for (int i = 0; i < data.length(); i++) {
			if(i>=8 && i<=9)
				dia.append(data.charAt(i));
			if(i >3 && i<=6)
				mes.append(data.charAt(i));
			if(i>=25 && i<=29)
				ano.append(data.charAt(i));
		}
		dataCompleta.append(dia);
		dataCompleta.append(" de ");
		dataCompleta.append(convertMesToPt(mes.toString()));
		dataCompleta.append(" de ");
		dataCompleta.append(ano);
		
		return dataCompleta.toString();
	}
	public static String convertMesToPt(String mesString){
		switch (mesString) {
		case "Jan":
			return "Jan";
		case "Feb":
			return "Fev";
		case "Mar":
			return "Mar";
		case "Apr":
			return "Abr";
		case "May":
			return "Mai";
		case "Jun":
			return "Jun";
		case "Jul":
			return "Jul";
		case "Aug":
			return "Ago";
		case "Sep":
			return "Set";
		case "Oct":
			return "Out";
		case "Nov":
			return "Nov";
		case "Dec":
			return "Dez";
		default:
			return "";
		}
	}
}

