package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.DiariaReservada;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;

public class DiariaReservadaDAO {
	private EntityManager em;

	public DiariaReservadaDAO() {
		super();
	}

	public void salva(DiariaReservada dr) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(dr);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(DiariaReservada dr) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (dr.getId() == null) {
			em.persist(dr);
		} else {
			em.merge(dr);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaReservada dr = em.find(DiariaReservada.class, id);
		em.remove(dr);
		em.getTransaction().commit();
		em.close();
	}

	public DiariaReservada busca(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaReservada dr = em.find(DiariaReservada.class, id);
		em.getTransaction().commit();
		em.close();
		return dr;
	}

	public List<DiariaReservada> buscaDiarias() {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em.createQuery("select d from DiariaReservada d", DiariaReservada.class);
		List<DiariaReservada> diarias = query.getResultList();
		em.close();
		return diarias;
	}

	public List<DiariaReservada> buscaPorData(Date data) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em.createQuery("select v from DiariaReservada v where v.data = :data",
				DiariaReservada.class);
		query.setParameter("data", data);
		List<DiariaReservada> diarias = query.getResultList();
		em.close();
		return diarias;
	}

	public List<DiariaReservada> buscaPorQuarto(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaReservada> query = em.createQuery("select v from DiariaReservada v where v.quarto = :quarto",
				DiariaReservada.class);
		query.setParameter("quarto", quarto);
		List<DiariaReservada> diarias = query.getResultList();
		em.close();
		return diarias;
	}
}
