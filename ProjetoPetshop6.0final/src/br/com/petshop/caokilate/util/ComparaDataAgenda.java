package br.com.petshop.caokilate.util;

import java.util.Comparator;

import br.com.petshop.caokilate.entidades.Agendamento;

public class ComparaDataAgenda implements Comparator<Agendamento> {

	@Override
	public int compare(Agendamento o1, Agendamento o2) {
		// TODO Auto-generated method stub
		return o1.dataInt() - o2.dataInt();
	}

}
