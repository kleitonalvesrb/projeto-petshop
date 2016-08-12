package br.com.petshop.caokilate.enumeradores;

public enum UserType {
	 
	    Administrador(1),
	 Funcionario(2),
	 Cliente(3);
	    
	    private int values;

	    private UserType(int values){
	        setValues(values);
	    }
	    public static UserType findTypeByValue(int value){
	        UserType []user = UserType.values();
	        for (UserType users : user) {
	            if(users.values == value)
	                return users;
	        }
	        return null;
	       
	    }
	    
	    /**
	     * @return the values
	     */
	    public int getValues() {
	        return values;
	    }

	    /**
	     * @param values the values to set
	     */
	    public void setValues(int values) {
	        this.values = values;
	    }

	   

}
