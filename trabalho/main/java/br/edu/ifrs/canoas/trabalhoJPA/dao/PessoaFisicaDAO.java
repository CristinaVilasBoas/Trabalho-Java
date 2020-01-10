package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaFisica;

public class PessoaFisicaDAO {

	private EntityManager em;

	public PessoaFisicaDAO() {
		super();
	}

	public void salva(PessoaFisica pessoaFisica) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(pessoaFisica);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(PessoaFisica pessoaFisica) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (pessoaFisica.getId() == null) {
			em.persist(pessoaFisica);
		} else {
			em.merge(pessoaFisica);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaFisica pessoaFisica = em.find(PessoaFisica.class, id);
		em.remove(pessoaFisica);
		em.getTransaction().commit();
		em.close();
	}

	public PessoaFisica buscaID(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		PessoaFisica pessoaFisica = em.find(PessoaFisica.class, id);
		em.getTransaction().commit();
		em.close();
		return pessoaFisica;
	}

	public List<PessoaFisica> buscaPorTodos() {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica p", PessoaFisica.class);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaFisica> buscaPorEmail(String email) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select v from PessoaFisica v where v.email = :email",
				PessoaFisica.class);
		query.setParameter("email", email);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaFisica> buscaPorRg(String rg) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica p where p.rg = :rg",
				PessoaFisica.class);
		query.setParameter("rg", rg);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}

	public List<PessoaFisica> buscaPorCpf(String cpf) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica p where p.cpf = :cpf",
				PessoaFisica.class);
		query.setParameter("cpf", cpf);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
	
	public List<PessoaFisica> buscaPorNome(String Nome) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica p where p.nome = :nome",
				PessoaFisica.class);
		query.setParameter("nome", Nome);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
	
	public List<PessoaFisica> buscaPorTelefone(String Telefone) {
		em = EntityManagerUtil.getEM();
		TypedQuery<PessoaFisica> query = em.createQuery("select p from PessoaFisica p where p.telefone = :telefone",
				PessoaFisica.class);
		query.setParameter("telefone", Telefone);
		List<PessoaFisica> pessoas = query.getResultList();
		em.close();
		return pessoas;
	}
}
