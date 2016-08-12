package br.com.petshop.caokilate.ordenadores;

import java.util.Comparator;

import br.com.petshop.caokilate.entidades.Servico;

public class OrdenaServicoOrdeAlfabetica implements Comparator<Servico>{

	@Override
	public int compare(Servico o1, Servico o2) {
		return o1.getDescricao().compareToIgnoreCase(o2.getDescricao());
	}

}
