package br.edu.ifrs.canoas.trabalhoJPA.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.EntityManagerUtil.EntityManagerUtil;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.DiariaAvulsa;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Pessoa;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;

public class DiariaAvulsaDAO {
	private EntityManager em;

	public DiariaAvulsaDAO() {
		super();
	}

	public void salva(DiariaAvulsa da) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.persist(da);
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(DiariaAvulsa da) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		if (da.getId() == null) {
			em.persist(da);
		} else {
			em.merge(da);
		}
		em.getTransaction().commit();
		em.close();
	}

	public void remove(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaAvulsa da = em.find(DiariaAvulsa.class, id);
		em.remove(da);
		em.getTransaction().commit();
		em.close();
	}

	public DiariaAvulsa busca(Long id) {
		em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		DiariaAvulsa da = em.find(DiariaAvulsa.class, id);
		em.getTransaction().commit();
		em.close();
		return da;
	}

	public List<DiariaAvulsa> buscaDiarias() {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("select d from DiariaAvulsa d", DiariaAvulsa.class);
		List<DiariaAvulsa> diarias = query.getResultList();
		em.close();
		return diarias;
	}

	public List<DiariaAvulsa> buscaPorData(Date data) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("select v from DiariaAvulsa v where v.data = :data",
				DiariaAvulsa.class);
		query.setParameter("data", data);
		List<DiariaAvulsa> diarias = query.getResultList();
		em.close();
		return diarias;
	}

	public List<DiariaAvulsa> buscaPorQuarto(Quarto quarto) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("select v from DiariaAvulsa v where v.quarto = :quarto",
				DiariaAvulsa.class);
		query.setParameter("quarto", quarto);
		List<DiariaAvulsa> diarias = query.getResultList();
		em.close();
		return diarias;
	}

	public List<DiariaAvulsa> buscaPorPessoa(Pessoa pessoa) {
		em = EntityManagerUtil.getEM();
		TypedQuery<DiariaAvulsa> query = em.createQuery("select v from DiariaAvulsa v where v.pessoa = :pessoa",
				DiariaAvulsa.class);
		query.setParameter("pessoa", pessoa);
		List<DiariaAvulsa> diarias = query.getResultList();
		em.close();
		return diarias;
	}
}
