package br.edu.ifrs.canoas.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.QuartoDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.TipoDeQuarto;

public class QuartoDaoTest {
	QuartoDAO quartoDAO = new QuartoDAO();

	@Test
	public void testSalvaQuarto() {
		Quarto quarto = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.DUPLO).build();
		quartoDAO.salva(quarto);
		// 1
		assertThat(quarto.getId()).isNotNull();
		// 2
		assertThat(quarto.getNumero()).isNotNull();
		// 3
		assertThat(quarto.getTipoDeQuarto()).isNotNull();
		// 4
		assertThat(quarto.getNumero()).isEqualTo(quarto.getNumero());
		// 5
		assertThat(quarto.getNumero()).isEqualTo("1");
		// 6
		assertThat(quarto.getTipoDeQuarto()).isEqualTo(quarto.getTipoDeQuarto());
	}

	@Test
	public void testaAtualizaQuarto() {

		Quarto quarto = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.MASTER).build();
		quartoDAO.salva(quarto);

		// 1
		assertThat(quarto.getId()).isNotNull();
		// 2
		assertThat(quarto.getNumero()).as("1");
		// 3
		assertThat(quarto.getNumero()).isNotNull();
		// 4
		assertThat(quarto.getNumero()).isEqualTo("1");

		quarto.setNumero("2");
		quartoDAO.atualiza(quarto);
		Quarto novo = quartoDAO.busca(quarto.getId());

		// 5
		assertThat(novo.getNumero()).isEqualTo("2");
		// 6
		assertThat(novo.getNumero()).isNotNull();
		// 7
		assertThat(novo.getNumero()).isNotEqualTo("1");
	}

	@Test
	public void testaBuscaTodos() {
		Quarto quarto1 = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.STANDART).build();
		quartoDAO.salva(quarto1);

		Quarto quarto2 = Quarto.builder().numero("2").tipoDeQuarto(TipoDeQuarto.TRIPLO).build();
		quartoDAO.salva(quarto2);

		List<Quarto> quartos = quartoDAO.buscaTodos();

		// 1
		assertThat(quartos).size().isGreaterThan(1);
		// 2
		assertThat(quartos).size().isNotNull();
		// 3
		assertThat(quartos).size().isGreaterThan(0);
		// 4
		assertThat(quartos).isNotEmpty();
		// 5
		assertThat(quartos).size().isGreaterThanOrEqualTo(2);
	}

	@Test
	public void testaRemoveQuarto() {
		Quarto quarto = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.STANDART).build();
		quartoDAO.salva(quarto);

		// 1
		assertThat(quarto.getId()).isNotNull();
		// 2
		assertThat(quarto.getNumero()).isEqualTo("1");
		// 3
		assertThat(quarto.getNumero()).isNotNull();
		// 4
		assertThat(quarto.getTipoDeQuarto().compareTo(quarto.getTipoDeQuarto()));

		quartoDAO.remove(quarto.getId());

		// 5
		assertThat(quartoDAO.busca(quarto.getId())).isNull();
	}

	@Test
	public void testaPorNumeroDeQuarto() {
		Quarto quarto = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.MASTER).build();
		quartoDAO.salva(quarto);

		Quarto quartoBD = quartoDAO.buscaPorNumero("1").get(0);
		// 1
		assertThat(quartoBD.getNumero()).isEqualTo("1");
		// 2
		assertThat(quartoBD.getId()).isNotNull();
		// 3
		assertThat(quartoBD.getNumero()).isNotNull();
		// 4
		assertThat(quartoBD.getNumero()).contains(quarto.getNumero());
		// 5
		assertThat(quartoBD).isEqualTo(quartoBD);
	}

	@Test
	public void testAtualizaQuartoComIdNulo() {
		Quarto quarto = Quarto.builder().numero("1").tipoDeQuarto(TipoDeQuarto.MASTER).build();
		quartoDAO.salva(quarto);

		quarto.setId(null);
		// 1
		assertThat(quarto.getId()).isNull();
		quartoDAO.atualiza(quarto);
		// 2
		assertThat(quarto.getId()).isNotNull();
	}
}
