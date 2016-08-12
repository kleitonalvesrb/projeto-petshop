package br.com.petshop.caokilate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.petshop.caokilate.entidades.Agendamento;
import br.com.petshop.caokilate.entidades.Animal;
import br.com.petshop.caokilate.entidades.Servico;
import br.com.petshop.caokilate.entidades.Usuario;

public class Gerente {
	private EntityManager manager;
	
	public Gerente(){
		setManager(Persistence.createEntityManagerFactory("JPA_UNIT").createEntityManager());
	}
	public void persist(Object obj){
		getManager().getTransaction().begin();
		getManager().persist(obj);
		getManager().getTransaction().commit();
	}
	public void atualizar(Object obj){
		getManager().getTransaction().begin();
		getManager().merge(obj);
		getManager().getTransaction().commit();
		System.out.println("atualizou");
	}
	public EntityManager getManager() {
		return manager;
	}
	

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	public List<Agendamento>buscaAgendaCliente(String cpf){
		String jpql = "Select a from agendamento a where a.usuario.cpf = ?1";
		Query query = getManager().createQuery(jpql);
		query.setParameter(1, cpf);
		List<Agendamento> agenda = query.getResultList();
		return agenda;
	}
	public List<Agendamento> buscaAgendaAnimal(int cod){
		String jpql = "Select a from agendamento a where a.animal.codigo = ?1";
		Query query = getManager().createQuery(jpql);
		query.setParameter(1, cod);
		List<Agendamento> agendamentos = query.getResultList();
		return agendamentos;
	}
	public Agendamento findAgendaByCod(int cod){
		String jpql = "Select a from agendamento a where a.codigo = ?1";
		Query query = getManager().createQuery(jpql);
		query.setParameter(1,cod);
		return (Agendamento) query.getSingleResult();
	}
	public Usuario findUserByLogin(String login){
		try {
			String jpql = "Select u from Usuario u where u.login = ?1";
			Query query = getManager().createQuery(jpql);
			query.setParameter(1, login);
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado encontrado");
		}
		return null; 
	}
	public Servico findServicoByCod(int cod){
		String jpql = "select s from Servico s where s.codigo = ?1";
		Query query = getManager().createQuery(jpql);
		query.setParameter(1, cod);
		Servico s =(Servico)query.getSingleResult();
		return s;
	}
	public Animal findAnimalById(int id){
		String jpql = "Select a from Animal a where a.codigo = ?1";
		Query query = getManager().createQuery(jpql);
		query.setParameter(1, id);
		Animal a = (Animal)query.getSingleResult();
		return a;
	}
	
	public void deleteUsuario(Object obj){
		getManager().getTransaction().begin();
		getManager().remove(obj);
		getManager().getTransaction().commit();
		System.out.println("excluiu");
	}
	public Usuario findUserByCpf(String cpf){
		try {
			String jpql = "Select u from Usuario u where u.cpf = ?1";
			Query query = getManager().createQuery(jpql);
			query.setParameter(1, cpf);
			Usuario user = (Usuario) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			System.out.println("Nenhum resultado encontrado");
		}
		return null; 
	}
	public List<Usuario> buscaTodos(){
		EntityManager em = getManager();
		Query q = em.createNamedQuery("Usuario.buscarTodos");
		
		List<Usuario> usuarios = q.getResultList();
		em.close();
		return usuarios;
	}
	public List<Agendamento> buscarTodosAgendamento(){
		Query query = getManager().createNamedQuery("Agendamento.buscarTodos");
		List<Agendamento> agendamentos = query.getResultList();
		getManager().close();
		return agendamentos;
	}
	public List<Servico> buscaTodosServico(){
		EntityManager em = getManager();
		Query query = em.createNamedQuery("Servico.buscarTodos");
		List<Servico> servicos = query.getResultList();
		em.close();
		return servicos;
		
	}
	
}
