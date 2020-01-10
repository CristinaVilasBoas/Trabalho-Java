package br.edu.ifrs.canoas.trabalhoJPA.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String logradouro;
	@NotNull
	private int numero;
	@NotEmpty
	private String complemento;
	@NotEmpty
	private String cep;
	@NotEmpty
	private String bairro;
	@NotEmpty
	@Size(min = 2, max = 2)
	private String uf;
	@OneToOne(mappedBy = "endereco")
	private Pessoa pessoa;

	public Endereco() {
		super();
	}

	@Builder(builderMethodName = "builder")
	public Endereco(String logradouro, int numero, String complemento, String cep, String bairro, String uf) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.uf = uf;
	}

}
