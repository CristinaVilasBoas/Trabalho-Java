package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String nome;
	private String email;
	@NotNull
	@Column(nullable = false)
	private String telefone;
	@OneToOne(orphanRemoval = true)
	private Endereco endereco;

	@OneToMany(mappedBy = "pessoa")
	private List<Reserva> reservas = new ArrayList<Reserva>();

	@OneToMany(mappedBy = "pessoa")
	private List<DiariaAvulsa> diariasAvulsas = new ArrayList<DiariaAvulsa>();

	public Pessoa() {
	}

	public void addDiariaAvulsa(DiariaAvulsa diariaAvulsa) {
		diariasAvulsas.add(diariaAvulsa);
		diariaAvulsa.setPessoa(this);
	}

	public void addReserva(Reserva reserva) {
		reservas.add(reserva);
	}

	public Pessoa(String nome, String email, String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
}