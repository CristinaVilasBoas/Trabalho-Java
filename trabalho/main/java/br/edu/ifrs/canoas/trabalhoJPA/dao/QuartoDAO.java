package br.edu.ifrs.canoas.trabalhoJPA.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;

public class QuartoDAO {
	private EntityManager em;

	public QuartoDAO() {
		super();
	}

	public void salva(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(quarto);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (quarto.getId() == null) {
			em.persist(quarto);
		} else {
			em.merge(quarto);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Quarto quarto = em.find(Quarto.class, id);
		em.remove(quarto);
		em.getTransaction().commit();
		em.close();
	}

	public Quarto busca(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Quarto quarto = em.find(Quarto.class, id);
		em.getTransaction().commit();
		em.close();
		return quarto;
	}
	public List<Quarto> buscaTodos() {
		em = EntityManagerUtil.getEM();
		TypedQuery<Quarto> query = em.createQuery("select r from Quarto r", Quarto.class);
		List<Quarto> quarto = query.getResultList();
		em.close();
		return quarto;
	}
	public List<Quarto> buscaPorNumero(String numero) {
		em = EntityManagerUtil.getEM();
		TypedQuery<Quarto> query = em.createQuery("select v from Quarto v where v.numero =  :numero", Quarto.class);
		query.setParameter("numero", numero);
		List<Quarto> quartos = query.getResultList();
		em.close();
		return quartos;
	}
}
