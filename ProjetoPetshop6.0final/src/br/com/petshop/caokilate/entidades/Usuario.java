package br.com.petshop.caokilate.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.oracle.webservices.internal.api.EnvelopeStyle;
import com.sun.xml.internal.bind.v2.runtime.Name;

import br.com.petshop.caokilate.enumeradores.UserType;
@Entity(name = "Usuario")
@NamedQueries(
			@NamedQuery(name = "Usuario.buscarTodos", query = "Select u from Usuario u")
		)
public class Usuario {
	@Id
	@Column(length=16, nullable = false)
	private String cpf;
	
	@Enumerated(EnumType.STRING)
    private UserType tipo;
    private String nome;
    private String senha;
    private String login;
    private String fone;
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="idEndereco")
    private Endereco endereco;
    
    
    
    @JoinColumn(name="cpfDono")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Animal> animais = new ArrayList<Animal>();
    
    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
    @JoinColumn(name ="cpfSolicitante")
    private List<Consulta> consultas = new ArrayList<Consulta>();
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
    @JoinColumn(name = "cpfSolicitante")
    private List<Agendamento> agendamentos = new ArrayList<Agendamento>();
    
    public Usuario(){
        setAnimais(new ArrayList<Animal>());
        setConsultas(new ArrayList<Consulta>());
        setAgendamentos(new ArrayList<Agendamento>());
    }
   /*public Usuario(String cpf, UserType tipo, String nome, String senha,String login, String fone,
                    String email, Endereco endereco){
    	setCpf(cpf);
        setTipo(tipo);
        setNome(nome);
        setSenha(senha);
        setLogin(login);
        setFone(fone);
        setEmail(email);
        setEndereco(endereco);
        
    }*/ 

    @Override
    public String toString() {
       return "Nome "+getNome()+" tipo "+getTipo()+" Senha "+getSenha()+" Login "+getLogin()+" "+
    		   getEndereco()+" Animais "+getAnimais()+"\n consulta "+getConsultas();
    }
    
//    

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	   /**
     * 
     * @return apenas o primeito nome
     */
    public String getPrimeiroNome(){
    	return getNome().split(" ")[0].toString();
    }
	/**
     * @return the codigo
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the tipo
     */
    public UserType getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(UserType tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * @param fone the fone to set
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
	public List<Animal> getAnimais() {
		return animais;
	}
	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
    
    
    
}
