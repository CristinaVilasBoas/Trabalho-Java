package br.edu.ifrs.canoas.trabalhoJPA.pojo;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@DiscriminatorValue(value = "Pesoa_Juridica")
public class PessoaJuridica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String razao;
	@NotEmpty
	private String cnpj;
	@NotEmpty
	private String estadual;
	@NotEmpty
	private String municipal;

	public PessoaJuridica() {
		super();
	}

	@Builder(builderMethodName = "builder")
	public PessoaJuridica(String nome, String email, String telefone, String razao, String cnpj, String estadual,
			String municipal) {
		super(nome, email, telefone);
		this.razao = razao;
		this.cnpj = cnpj;
		this.estadual = estadual;
		this.municipal = municipal;
	}
}