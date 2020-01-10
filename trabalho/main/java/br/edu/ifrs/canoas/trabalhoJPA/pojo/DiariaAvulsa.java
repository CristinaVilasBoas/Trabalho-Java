package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue(value = "DiariaAvulsa")
public class DiariaAvulsa extends Diaria implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "PESSOA_IDA")
	private Pessoa pessoa;

	public DiariaAvulsa() {
	}

	@Builder(builderMethodName = "builder")
	public DiariaAvulsa(Date data) {
		super(data);
	}
}
