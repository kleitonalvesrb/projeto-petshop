package br.com.petshop.caokilate.enumeradores;

public enum TipoAnimal {
	CACHORRO(1),
	GATO(2),
	ROEDOR(3),
	EQUINO(4);
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	private TipoAnimal(int value){
		setValue(value);
	}
	
	public static TipoAnimal findByValue(int value){
		TipoAnimal []tipo = TipoAnimal.values();
		for (TipoAnimal tipoAnimal : tipo) {
			if(tipoAnimal.getValue() == value)
				return tipoAnimal;
		}
		return null;
	}
}
