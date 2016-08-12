package br.com.petshop.caokilate.enumeradores;

public enum PorteAnimal {
	PEQUENO(1),
	MEDIO(2),
	GRANDE(3);
	
	private int value;
	
	private PorteAnimal(int value){
		setValue(value);
	}

	public static PorteAnimal findByValue(int value){
		PorteAnimal [] porte = PorteAnimal.values();
		for (PorteAnimal porteAnimal : porte) {
			if(porteAnimal.getValue() == value)
				return porteAnimal;
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
