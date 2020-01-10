package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Pessoa;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Reserva;

public class ReservaDAO {
	private EntityManager em;

	public ReservaDAO() {
	}

	public void salva(Reserva reserva) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(reserva);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(Reserva reserva) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (reserva.getId() == null) {
			em.persist(reserva);
		} else {
			em.merge(reserva);
		}
		em.getTransaction().commit();
		em.close();;
	}

	public void remove(Integer id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Reserva reserva = em.find(Reserva.class, id);
		em.remove(reserva);
		em.getTransaction().commit();
		em.close();
	}

	public Reserva busca(Integer id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		Reserva reserva = em.find(Reserva.class, id);
		em.getTransaction().commit();
		em.close();
		return reserva;
	}

	public List<Reserva> busca() {
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("select r from Reserva r", Reserva.class);
		List<Reserva> reserva = query.getResultList();
		em.close();
		return reserva;
	}

	public List<Reserva> buscaPorValor(Double valor) {
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("select v from Reserva v where v.valor = :valor", Reserva.class);
		query.setParameter("valor", valor);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}

	public List<Reserva> buscaPorData(Date data) {
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("select v from Reserva v where v.data =  :data", Reserva.class);
		query.setParameter("data", data);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}
	public List<Reserva> buscaPorPessoa(Pessoa pessoa) {
		em = EntityManagerUtil.getEM();
		TypedQuery<Reserva> query = em.createQuery("select v from Reserva v where v.pessoa =  :pessoa", Reserva.class);
		query.setParameter("pessoa", pessoa);
		List<Reserva> reservas = query.getResultList();
		em.close();
		return reservas;
	}
}
