package br.edu.ifrs.canoas.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.EnderecoDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Endereco;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaFisica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Sexo;

public class PessoaFisicaDaoTest {

	PessoaFisicaDAO pfd = new PessoaFisicaDAO();
	EnderecoDAO endDAO = new EnderecoDAO();
	private static Validator validator;

	@Before
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testeSalvaPessoaFisica() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);
		// 1
		assertThat(pf.getId()).isNotNull();
		// 2
		assertThat(pf.getCpf()).isEqualTo("000.000.008-00");
		// 3
		assertThat(pf.getRg()).isEqualTo("132456798");
		// 4
		assertThat(pf.getTelefone()).isEqualTo("(51) 3535-3535");
		// 5
		assertThat(pf.getEmail()).isEqualTo("pessoa@gmail.com");
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pf.getNome()).isEqualTo("Pessoa");
		// 8
		assertThat(pf.getSexo()).isNotNull();
		// 9
		assertThat(pf.getEndereco()).isNotNull();
		// 10
		assertThat(pf.getSexo()).isEqualTo(pf.getSexo());
		// 11
		assertThat(pf.getEndereco()).isEqualTo(pf.getEndereco());
		// 12
		assertThat(pf.getData()).isNotNull();
		// 13
		assertThat(pf.getData()).isEqualTo(data);
	}

	@Test
	public void testaBuscaTodasAsPessoasFisicas() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		PessoaFisica pf1 = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end1 = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);
		Set<ConstraintViolation<PessoaFisica>> violations1 = validator.validate(pf1);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		endDAO.salva(end1);
		pf1.setEndereco(end1);
		pfd.salva(pf1);

		List<PessoaFisica> pessoas = pfd.buscaPorTodos();

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
		// 4
		assertThat(violations1.isEmpty()).isTrue();
		// 5
		assertThat(pf.getData()).isNotNull();
		// 6
		assertThat(pf.getData()).isEqualTo(data);
		
	}

	@Test
	public void testaAtualizarPessoaFisica() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		// 1
		assertThat(pf.getId()).isNotNull();
		// 2
		assertThat(pf.getNome()).as("Pessoa1");
		// 3
		assertThat(violations.isEmpty()).isTrue();
		// 4
		assertThat(pf.getNome()).isNotNull();
		// 5
		assertThat(pf.getSexo()).isNotNull();
		// 6
		assertThat(pf.getEndereco()).isNotNull();
		// 7
		assertThat(pf.getSexo()).isEqualTo(pf.getSexo());
		// 8
		assertThat(pf.getEndereco()).isEqualTo(pf.getEndereco());
		// 9
		assertThat(pf.getData()).isNotNull();
		// 10
		assertThat(pf.getData()).isEqualTo(data);

		pf.setNome("Cristina");
		pfd.atualiza(pf);
		PessoaFisica novo = pfd.buscaID(pf.getId());

		// 11
		assertThat(novo.getNome()).isEqualTo("Cristina");
		// 12
		assertThat(novo.getNome()).isNotNull();
		// 13
		assertThat(novo.getNome()).isNotEqualTo("Pessoa1");
		// 14
		assertThat(novo.getId()).isNotNull();
		// 15
		assertThat(novo.getSexo()).isNotNull();
		// 16
		assertThat(novo.getEndereco()).isNotNull();
		// 17
		assertThat(novo.getSexo()).isEqualTo(novo.getSexo());
		// 18
		assertThat(novo.getEndereco()).isEqualTo(novo.getEndereco());
		// 19
		assertThat(novo.getData()).isNotNull();
		// 20
		assertThat(novo.getData()).isEqualTo(novo.getData());

	}

	@Test
	public void testaRemovePessoaFisica() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();
		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);
		
		// 1
		assertThat(pf.getId()).isNotNull();
		// 2
		assertThat(pf.getCpf()).isEqualTo("000.000.008-00");
		// 3
		assertThat(pf.getRg()).isEqualTo("132456798");
		// 4
		assertThat(pf.getTelefone()).isEqualTo("(51) 3535-3535");
		// 5
		assertThat(pf.getEmail()).isEqualTo("pessoa@gmail.com");
		// 6
		assertThat(violations.isEmpty()).isTrue();
		// 7
		assertThat(pf.getNome()).isEqualTo("Pessoa");
		// 8
		assertThat(pf.getSexo()).isNotNull();
		// 9
		assertThat(pf.getEndereco()).isNotNull();
		// 10
		assertThat(pf.getSexo()).isEqualTo(pf.getSexo());
		// 11
		assertThat(pf.getEndereco()).isEqualTo(pf.getEndereco());
		// 12
		assertThat(pf.getData()).isNotNull();
		// 13
		assertThat(pf.getData()).isEqualTo(data);

		pfd.remove(pf.getId());

		// 14
		assertThat(pfd.buscaID(pf.getId())).isNull();
		// 15
		assertThat(pfd.buscaPorTodos()).doesNotContain(pf);
	}

	@Test
	public void testaBuscaPorCpf() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();
		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		PessoaFisica pessoaBD = pfd.buscaPorCpf("000.000.008-00").get(0);
		// 1
		assertThat(pessoaBD.getCpf()).isEqualTo("000.000.008-00");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getCpf()).isNotNull();
		// 4
		assertThat(pessoaBD.getCpf()).contains(pf.getCpf());
		// 5
		assertThat(pessoaBD.getCpf()).containsSequence("000.000.008-00");
		// 6
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testaBuscaPorRg() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();
		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		PessoaFisica pessoaBD = pfd.buscaPorRg("132456798").get(0);
		// 1
		assertThat(pessoaBD.getRg()).isEqualTo("132456798");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getRg()).isNotNull();
		// 4
		assertThat(pessoaBD.getRg()).containsSequence(pf.getRg());
		// 5
		assertThat(pessoaBD.getRg()).contains(pf.getRg());
		// 6
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testaBuscaPorEmail() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		PessoaFisica pessoaBD = pfd.buscaPorEmail("pessoa@gmail.com").get(0);
		// 1
		assertThat(pessoaBD.getEmail()).isEqualTo("pessoa@gmail.com");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getEmail()).containsSequence(pf.getEmail());
		// 4
		assertThat(pessoaBD.getEmail()).isNotNull();
		// 5
		assertThat(pessoaBD.getEmail()).contains(pf.getEmail());
		// 6
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testaBuscaPorNome() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Nome Buscado").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();
		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		PessoaFisica pessoaBD = pfd.buscaPorNome("Nome Buscado").get(0);
		// 1
		assertThat(pessoaBD.getNome()).isEqualTo("Nome Buscado");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getEmail()).isNotNull();
		// 4
		assertThat(pessoaBD.getNome()).contains(pf.getNome());
		// 5
		assertThat(pessoaBD.getNome()).containsSequence(pf.getNome());
		// 6
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testaBuscaPorTelefone() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("NomeBuscado").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("9999-9999").build();

		Endereco end = Endereco.builder().logradouro("rua1").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pf);

		endDAO.salva(end);
		pf.setEndereco(end);
		pfd.salva(pf);

		PessoaFisica pessoaBD = pfd.buscaPorTelefone("9999-9999").get(0);
		// 1
		assertThat(pessoaBD.getTelefone()).isEqualTo("9999-9999");
		// 2
		assertThat(pessoaBD.getId()).isNotNull();
		// 3
		assertThat(pessoaBD.getTelefone()).isNotNull();
		// 4
		assertThat(pessoaBD.getTelefone()).contains(pf.getTelefone());
		// 5
		assertThat(pessoaBD.getTelefone()).containsSequence(pf.getTelefone());
		// 6
		assertThat(violations.isEmpty()).isTrue();
	}

	@Test
	public void testAtualizaPessoaFisicaComIdNulo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("NomeBuscado").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("9999-9999").build();
		pfd.salva(pf);

		pf.setId(null);
		// 1
		assertThat(pf.getId()).isNull();
		pfd.atualiza(pf);
		// 2
		assertThat(pf.getId()).isNotNull();
	}

}