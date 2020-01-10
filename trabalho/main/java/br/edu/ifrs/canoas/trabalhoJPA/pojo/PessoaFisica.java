package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue(value = "Pessoa_Fisica")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Size(min = 14, max = 14)
	private String cpf;
	@NotEmpty
	private String rg;
	private Date data;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@ManyToMany(mappedBy = "fisicas")
	private List<Diaria> diarias = new ArrayList<Diaria>();

	public PessoaFisica() {
		super();
	}

	@Builder(builderMethodName = "builder")
	public PessoaFisica(String nome, String email, String telefone, String cpf, String rg, Date data, Sexo sexo) {
		super(nome, email, telefone);
		this.cpf = cpf;
		this.rg = rg;
		this.data = data;
		this.sexo = sexo;
	}
}