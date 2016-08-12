package br.com.petshop.caokilate.enumeradores;

public enum TipoCompromisso {
	SERVICO(1),
	CONSULTA(2);
	
	private int value;
	
	private TipoCompromisso(int value){
		setValue(value);
	}
	
	public static TipoCompromisso findByValue(int value){
		TipoCompromisso [] tipo = TipoCompromisso.values();
		for (TipoCompromisso tipoCompromisso : tipo) {
			if(tipoCompromisso.getValue() == value)
				return tipoCompromisso;
		}
		return null;
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
