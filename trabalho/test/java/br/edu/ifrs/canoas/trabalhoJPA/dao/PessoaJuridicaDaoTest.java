package br.edu.ifrs.canoas.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.EnderecoDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Endereco;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaJuridica;

public class PessoaJuridicaDaoTest {

	private static Validator validator;
	PessoaJuridicaDAO pfd = new PessoaJuridicaDAO();
	EnderecoDAO endDAO = new EnderecoDAO();

	@Before
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testSalvaPessoaJuridica() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		// 1
		assertThat(pj.getId()).isNotNull();
		// 2
		assertThat(pj.getNome()).isEqualTo(pj.getNome());
		// 3
		assertThat(pj.getCnpj()).isEqualTo("35.773.727/0001-96");
		// 4
		assertThat(pj.getRazao()).isEqualTo("Indústrias Ltda");
		// 5
		assertThat(pj.getEstadual()).isEqualTo("776/1743607");
		// 6
		assertThat(pj.getEmail()).isEqualTo("pessoaJuridica@gmail.com");
		// 7
		assertThat(pj.getMunicipal()).isEqualTo("775/1743607");
		// 8
		assertThat(pj.getNome()).isEqualTo("Pessoa Juridica");
		// 9
		assertThat(pj.getTelefone()).isEqualTo("(55) 3976-0371");
		// 10
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testaAtualizaPessoaJuridica() {

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("123456").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		// 1
		assertThat(pj.getId()).isNotNull();
		// 2
		assertThat(pj.getNome()).as("Pessoa Juridica");
		// 3
		assertThat(pj.getNome()).isNotNull();

		pj.setNome("Novo nome");
		pfd.atualiza(pj);
		PessoaJuridica novo = pfd.busca(pj.getId());

		// 4
		assertThat(novo.getNome()).isEqualTo("Novo nome");
		// 5
		assertThat(novo.getNome()).isNotEqualTo("Pessoa Juridica");
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(novo.getNome()).isNotNull();
		// 8
		assertThat(novo.getId()).isNotNull();
	}

	@Test
	public void testaBuscaPorTodasAsPessoasJuridicas() {
		PessoaJuridica pj1 = PessoaJuridica.builder().nome("Pessoa Juridica1").email("pessoaJuridica1@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("123456").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		PessoaJuridica pj2 = PessoaJuridica.builder().nome("Pessoa Juridica1").email("pessoaJuridica1@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("123456").build();

		Endereco end2 = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj1);

		endDAO.salva(end2);
		pj2.setEndereco(end2);
		pfd.salva(pj2);

		endDAO.salva(end);
		pj1.setEndereco(end);
		pfd.salva(pj1);

		List<PessoaJuridica> pessoas = pfd.busca();

		// 1
		assertThat(pessoas).size().isGreaterThan(1);
		// 2
		assertThat(pessoas).size().isNotNull();
		// 3
		assertThat(pessoas).isNotEmpty();
		// 4
		assertThat(violations.isEmpty()).isTrue();
		// 5
		assertThat(pessoas).size().isGreaterThanOrEqualTo(2);
	}

	@Test
	public void testaRemovePessoaJuridica() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		// 1
		assertThat(pj.getId()).isNotNull();
		// 2
		assertThat(violations.isEmpty()).isTrue();

		pfd.remove(pj.getId());

		// 3
		assertThat(pfd.busca(pj.getId())).isNull();
	}

	@Test
	public void testaBuscaPorCnpj() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);
		PessoaJuridica pessoaBD = pfd.buscaPorCnpj("35.773.727/0001-96").get(0);
		// 1
		assertThat(pessoaBD.getCnpj()).isEqualTo("35.773.727/0001-96");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getCnpj()).isNotNull();
		// 4
		assertThat(pessoaBD.getCnpj()).isEqualTo(pj.getCnpj());
		// 5
		assertThat(pessoaBD.getCnpj()).contains(pj.getCnpj());
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pessoaBD.getCnpj()).isNotEqualTo("35.773.722/0001-96");
	}

	@Test
	public void buscaPorRazao() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);
		PessoaJuridica pessoaBD = pfd.buscaPorRazao("Indústrias Ltda").get(0);
		// 1
		assertThat(pessoaBD.getRazao()).isEqualTo("Indústrias Ltda");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getRazao()).isNotNull();
		// 4
		assertThat(pessoaBD.getRazao()).isEqualTo(pj.getRazao());
		// 5
		assertThat(pessoaBD.getRazao()).contains(pj.getRazao());
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pessoaBD.getId()).isNotEqualTo("Indústrias");
	}

	@Test
	public void buscaPorInscricaoEstadual() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		PessoaJuridica pessoaBD = pfd.buscaPorInscricaoEstadual("776/1743607").get(0);
		// 1
		assertThat(pessoaBD.getEstadual()).isEqualTo("776/1743607");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getEstadual()).isNotNull();
		// 4
		assertThat(pessoaBD.getEstadual()).isEqualTo(pj.getEstadual());
		// 5
		assertThat(pessoaBD.getEstadual()).contains(pj.getEstadual());
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pessoaBD.getEstadual()).isNotEqualTo("775/1743607");
	}

	@Test
	public void testaBuscaPorNome() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("NomeBuscado").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		PessoaJuridica pessoaBD = pfd.buscaPorNome("NomeBuscado").get(0);
		// 1
		assertThat(pessoaBD.getNome()).isEqualTo("NomeBuscado");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getEmail()).isNotNull();
		// 4
		assertThat(pessoaBD.getNome()).contains(pj.getNome());
		// 5
		assertThat(pessoaBD.getNome()).containsSequence(pj.getNome());
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 5
		assertThat(pessoaBD.getNome()).isNotEqualTo("Cristina");
	}

	@Test
	public void testaBuscaPorTelefone() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		PessoaJuridica pessoaBD = pfd.buscaPorTelefone("(55) 3976-0371").get(0);
		// 1
		assertThat(pessoaBD.getTelefone()).isEqualTo("(55) 3976-0371");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getTelefone()).isNotNull();
		// 4
		assertThat(pessoaBD.getTelefone()).contains(pj.getTelefone());
		// 5
		assertThat(pessoaBD.getTelefone()).containsSequence(pj.getTelefone());
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pessoaBD.getTelefone()).isNotEqualTo("(55) 3975-0371");
	}

	@Test
	public void testaBuscaPorInscricaoMunicipal() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pj);

		endDAO.salva(end);
		pj.setEndereco(end);
		pfd.salva(pj);

		PessoaJuridica pessoaBD = pfd.buscaPorInscricaoMunicipal("775/1743607").get(0);
		// 1
		assertThat(pessoaBD.getMunicipal()).isEqualTo("775/1743607");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getMunicipal()).isNotNull();
		// 4
		assertThat(pessoaBD.getMunicipal()).contains(pj.getMunicipal());
		// 5
		assertThat(violations.isEmpty()).isTrue();
		// 6
		assertThat(pessoaBD.getMunicipal()).isNotEqualTo("776/1743607");
	}

	@Test
	public void testAtualizaPessoaJuridicaComIdNulo() {
		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		pfd.salva(pj);

		pj.setId(null);
		// 1
		assertThat(pj.getId()).isNull();
		pfd.atualiza(pj);
		// 2
		assertThat(pj.getId()).isNotNull();
	}
}