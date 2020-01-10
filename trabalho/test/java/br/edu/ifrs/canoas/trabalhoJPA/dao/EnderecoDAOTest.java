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

import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.EnderecoDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Endereco;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaFisica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Sexo;

public class EnderecoDAOTest {

	PessoaFisicaDAO pfd = new PessoaFisicaDAO();
	EnderecoDAO edao = new EnderecoDAO();
	private static Validator validator;

	@Before
	public void init() {
		edao.removeAll();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testSalvaEnderecoValido() {

		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();

		assertThat(end.getLogradouro()).isEqualTo("rua");
		assertThat(end.getNumero()).isEqualTo(999);
		assertThat(end.getComplemento()).isEqualTo("Travessa X");
		assertThat(end.getCep()).isEqualTo("99999-999");
		assertThat(end.getBairro()).isEqualTo("Bairro Y");
		assertThat(end.getUf()).isEqualTo("RS");

		edao.salva(end);

	}

	@Test
	public void testAtualizaEnderecoComDadosValidos() {
		Endereco end = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();

		edao.salva(end);

		end.setLogradouro("rua 22");
		end.setNumero(766);
		end.setComplemento("Avenida U");
		end.setCep("88888-888");
		end.setBairro("Bairro XYZ");
		end.setUf("RJ");

		edao.atualiza(end);

		assertThat(end.getLogradouro()).isEqualTo("rua 22");
		assertThat(end.getNumero()).isEqualTo(766);
		assertThat(end.getComplemento()).isEqualTo("Avenida U");
		assertThat(end.getCep()).isEqualTo("88888-888");
		assertThat(end.getBairro()).isEqualTo("Bairro XYZ");
		assertThat(end.getUf()).isEqualTo("RJ");

	}

	@Test
	public void testBuscaEnderecoExistente() {
		Endereco end = Endereco.builder().logradouro("rua L").numero(777).complemento("Quadra 30").cep("55555-999")
				.bairro("Bairro 56").uf("PA").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();

		edao.salva(end);
		Endereco end2 = edao.busca(end.getId());

		assertThat(end).isEqualTo(end2);
	}

	@Test
	public void testRemoveEndereco() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		Endereco end = Endereco.builder().logradouro("rua L").numero(777).complemento("Quadra 30").cep("55555-999")
				.bairro("Bairro 56").uf("PA").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();

		edao.salva(end);

		Long idtest = end.getId();

		edao.remove(end.getId());

		assertThat(edao.busca(idtest)).isNull();

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).email("pessoa@gmail.com")
				.rg("132456798").cpf("000.000.008-00").data(data).telefone("(51) 3535-3535").build();

		Endereco end3 = Endereco.builder().logradouro("rua").numero(999).complemento("Travessa X").cep("99999-999")
				.bairro("Bairro Y").uf("RS").build();

		edao.salva(end3);
		pf.setEndereco(end3);
		pfd.salva(pf);
		
		edao.remove(end3.getId());
		
		assertThat(end3.getId()).isNotNull();
		

	}

	@Test
	public void testBuscaTodosOsEnderecosCadastradosNoBanco() {
		Endereco end = Endereco.builder().logradouro("rua L").numero(777).complemento("Quadra 30").cep("55555-999")
				.bairro("Bairro 56").uf("PA").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();

		edao.salva(end);

		List<Endereco> enderecos = edao.buscaTodos();

		assertThat(enderecos).isNotNull();
		assertThat(enderecos.size()).isGreaterThan(0);
		assertThat(enderecos).contains(end);

		for (Endereco endereco : enderecos) {
			assertThat(endereco.getId()).isNotNull();
		}
	}

	@Test
	public void testBuscaTodosOsEnderecosCadastradosPorBairro() {
		Endereco end = Endereco.builder().logradouro("rua H").numero(555).complemento("Quadra 38").cep("55565-999")
				.bairro("Bairro 56").uf("RR").build();

		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();
		edao.salva(end);

		Endereco end2 = Endereco.builder().logradouro("rua H").numero(555).complemento("Quadra 38").cep("55565-999")
				.bairro("Bairro 99").uf("RR").build();

		Set<ConstraintViolation<Endereco>> violations2 = validator.validate(end2);
		assertThat(violations2.isEmpty()).isTrue();
		edao.salva(end2);

		Condition<Endereco> endBairro = new Condition<Endereco>() {
			@Override
			public boolean matches(Endereco endereco) {
				return endereco.getBairro().equals("Bairro 56");
			}
		};

		List<Endereco> enderecos = edao.buscaPorBairro("Bairro 56");

		assertThat(enderecos).filteredOn(endBairro).containsOnly(end);
	}

	@Test
	public void testBuscaTodosOsEnderecosPorUF() {
		Endereco end = Endereco.builder().logradouro("rua H").numero(555).complemento("Quadra 38").cep("55565-999")
				.bairro("Bairro 56").uf("RR").build();
		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();
		edao.salva(end);

		Endereco end2 = Endereco.builder().logradouro("rua H").numero(555).complemento("Quadra 38").cep("55565-999")
				.bairro("Bairro 99").uf("RJ").build();
		Set<ConstraintViolation<Endereco>> violations2 = validator.validate(end);
		assertThat(violations2.isEmpty()).isTrue();
		edao.salva(end2);

		Condition<Endereco> endUF = new Condition<Endereco>() {
			@Override
			public boolean matches(Endereco endereco) {
				return endereco.getUf().equals("RR");
			}
		};

		List<Endereco> enderecos = edao.buscaPorUF("RR");

		assertThat(enderecos).filteredOn(endUF).containsOnly(end);
	}

	@Test
	public void testAtualizaEnderecoComIdNulo() {
		Endereco end = Endereco.builder().logradouro("rua H").numero(555).complemento("Quadra 38").cep("55565-999")
				.bairro("Bairro 56").uf("RR").build();
		Set<ConstraintViolation<Endereco>> violations = validator.validate(end);
		assertThat(violations.isEmpty()).isTrue();
		edao.salva(end);

		end.setId(null);

		edao.atualiza(end);

		assertThat(end.getId()).isNotNull();
	}
}
