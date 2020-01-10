package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Diaria implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date data;

	@ManyToMany
	private List<PessoaFisica> fisicas = new ArrayList<PessoaFisica>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Quarto quarto;

	public Diaria() {
		super();
	}

	public void addPessoaFisica(PessoaFisica pessoaFisica) {
		fisicas.add(pessoaFisica);
	}

	public Diaria(Date data) {
		super();
		this.data = data;
	}
}
