package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hibernate.exception.SQLGrammarException;

import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Endereco;

public class EnderecoDAO {
	private EntityManager em;

	public EnderecoDAO() {
		super();
	}

	public void salva(Endereco endereco) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(endereco);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Endereco endereco) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (endereco.getId() == null) {
			em.persist(endereco);
		} else {
			em.merge(endereco);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		try{
			Endereco endereco = em.find(Endereco.class, id);
			em.remove(endereco);
			em.getTransaction().commit();
		}catch(RollbackException e){
			e.getMessage();
		}
		em.close();

	}

	public void removeAll() {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.createQuery("update Pessoa set endereco = null where endereco is not null").executeUpdate();
		em.createQuery("delete from Endereco").executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	public Endereco busca(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Endereco endereco = em.find(Endereco.class, id);
		em.getTransaction().commit();
		em.close();
		return endereco;
	}

	public List<Endereco> buscaTodos() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<Long> ids = new ArrayList<Long>();
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		ids = em.createQuery("Select id from Endereco").getResultList();

		for (Long i : ids) {
			enderecos.add(em.find(Endereco.class, i));
		}

		em.close();

		return enderecos;
	}

	public List<Endereco> buscaPorBairro(String bairro) {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<Long> ids = new ArrayList<Long>();
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		ids = em.createQuery("SELECT id from Endereco Where bairro = :bairro").setParameter("bairro", bairro)
				.getResultList();

		for (Long i : ids) {
			enderecos.add(em.find(Endereco.class, i));
		}
		em.close();
		return enderecos;
	}

	public List<Endereco> buscaPorUF(String uf) {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<Long> ids = new ArrayList<Long>();
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		ids = em.createQuery("SELECT id from Endereco Where uf = :uf").setParameter("uf", uf).getResultList();

		for (Long i : ids) {
			enderecos.add(em.find(Endereco.class, i));
		}
		em.close();
		return enderecos;
	}
}
