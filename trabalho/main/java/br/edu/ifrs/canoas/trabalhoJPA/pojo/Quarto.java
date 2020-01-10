package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Builder;

@Data
@Entity
public class Quarto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numero;
	@Enumerated(EnumType.STRING)
	private TipoDeQuarto TipoDeQuarto;

	public Quarto() {
		super();
	}
	@Builder(builderMethodName = "builder")
	public Quarto(String numero, TipoDeQuarto tipoDeQuarto) {
		super();
		this.numero = numero;
		this.TipoDeQuarto = tipoDeQuarto;
	}

}
