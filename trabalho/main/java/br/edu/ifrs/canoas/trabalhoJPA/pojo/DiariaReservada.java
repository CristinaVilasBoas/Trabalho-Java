package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@DiscriminatorValue(value = "DiariaReservada")
public class DiariaReservada extends Diaria implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "diariaReservada")
	private Reserva reserva;

	public DiariaReservada() {
	}

	@Builder(builderMethodName = "builder")
	public DiariaReservada(Date data) {
		super(data);
	}
}
