package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.experimental.Builder;

@Data
@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;
	private Date data;
	private double valor;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DiariaReservada diariaReservada;
	
	@ManyToOne
	private Pessoa pessoa;

	public Reserva() {
		super();
	}
	@Builder(builderMethodName = "builder")
	public Reserva(Date data, double valor, DiariaReservada diariaReservada, Pessoa pessoa) {
		super();
		this.data = data;
		this.valor = valor;
		this.diariaReservada = diariaReservada;
		this.pessoa = pessoa;
	}
}
