package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaJuridica;

public class PessoaJuridicaDAO {
	private EntityManager em;

	public PessoaJuridicaDAO() {
		super();
	}

	public void salva(PessoaJuridica pessoaJuridica) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(pessoaJuridica);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(PessoaJuridica pessoaJuridica) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (pessoaJuridica.getId() == null) {
			em.persist(pessoaJuridica);
		} else {
			em.merge(pessoaJuridica);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class, id);
		em.remove(pessoaJuridica);
		em.getTransaction().commit();
		em.close();
	}

	public PessoaJuridica busca(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class, id);
		em.getTransaction().commit();
		em.close();
		return pessoaJuridica;
	}

	public List<PessoaJuridica> busca() {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p", PessoaJuridica.class);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaJuridica> buscaPorCnpj(String cnpj) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.cnpj = :cnpj",
				PessoaJuridica.class);
		query.setParameter("cnpj", cnpj);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaJuridica> buscaPorRazao(String razao) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.razao = :razao",
				PessoaJuridica.class);
		query.setParameter("razao", razao);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaJuridica> buscaPorInscricaoEstadual(String estadual) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.estadual = :estadual",
				PessoaJuridica.class);
		query.setParameter("estadual", estadual);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
	public List<PessoaJuridica> buscaPorNome(String Nome) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.nome = :nome",
				PessoaJuridica.class);
		query.setParameter("nome", Nome);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
	
	public List<PessoaJuridica> buscaPorTelefone(String Telefone) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.telefone = :telefone",
				PessoaJuridica.class);
		query.setParameter("telefone", Telefone);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
	public List<PessoaJuridica> buscaPorInscricaoMunicipal(String Municipal) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaJuridica> query = em.createQuery("select p from PessoaJuridica p where p.municipal = :municipal",
				PessoaJuridica.class);
		query.setParameter("municipal", Municipal);
		List<PessoaJuridica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
}
