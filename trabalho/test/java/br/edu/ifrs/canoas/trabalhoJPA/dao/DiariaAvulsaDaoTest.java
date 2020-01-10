package br.edu.ifrs.canoas.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.DiariaAvulsaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.DiariaAvulsa;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaFisica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaJuridica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Sexo;

public class DiariaAvulsaDaoTest {

	DiariaAvulsaDAO da = new DiariaAvulsaDAO();
	PessoaFisicaDAO pfd = new PessoaFisicaDAO();
	PessoaJuridicaDAO pjd = new PessoaJuridicaDAO();

	@Test
	public void testeSalvaDados() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("3").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getPessoa()).isEqualTo(pf);
		// 3
		assertThat(d.getData()).isEqualTo(data);
		// 4
		assertThat(d.getQuarto()).isNotNull();
		// 5
		assertThat(d.getQuarto()).isEqualTo(quarto);
		// 6
		assertThat(d.getPessoa()).isEqualTo(pf);
		// 7
		assertThat(pf.getData()).isNotNull();

		// PESSOA JURIDICA
		// 8
		assertThat(dJ.getId()).isNotNull();
		// 9
		assertThat(dJ.getPessoa()).isEqualTo(pj);
		// 10
		assertThat(dJ.getData()).isEqualTo(data1);
		// 11
		assertThat(dJ.getQuarto()).isNotNull();
		// 12
		assertThat(dJ.getQuarto()).isEqualTo(quartoJ);
		// 13
		assertThat(dJ.getPessoa()).isEqualTo(pj);
	}

	@Test
	public void testaBuscaPorTodasAsReservaAvulsas() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("5").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("6").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		List<DiariaAvulsa> diarias = da.buscaDiarias();

		// 1
		assertThat(diarias).size().isNotNull();
		// 2
		assertThat(diarias).isNotEmpty();
		// 3
		assertThat(diarias).size().isGreaterThan(2);
		// 4
		assertThat(d.getPessoa()).isEqualTo(pf);
		// 5
		assertThat(dJ.getPessoa()).isEqualTo(pj);
		// 6
		assertThat(diarias.contains(pj));
		// 7
		assertThat(diarias.contains(pf));
		// 8
		assertThat(pf.getData()).isNotNull();
	}

	@Test
	public void testaAtualizaReservaAvulsa() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("06-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("7").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data).build();
		Quarto quartoJ = Quarto.builder().numero("8").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getData()).isEqualTo(data);
		// 3
		assertThat(d.getPessoa()).isEqualTo(pf);
		// 4
		assertThat(d.getData()).isNotNull();
		// 5
		assertThat(d.getPessoa()).isNotNull();
		// 6
		assertThat(d.getQuarto()).isNotNull();
		// 8
		assertThat(d.getQuarto()).isEqualTo(quarto);

		// PESSOA JURIDICA
		// 9
		assertThat(dJ.getId()).isNotNull();
		// 10
		assertThat(dJ.getData()).isEqualTo(data);
		// 11
		assertThat(dJ.getPessoa()).isEqualTo(pj);
		// 12
		assertThat(dJ.getPessoa()).isNotNull();
		// 13
		assertThat(dJ.getData()).isNotNull();
		// 14
		assertThat(dJ.getQuarto()).isNotNull();
		// 15
		assertThat(dJ.getQuarto()).isEqualTo(quartoJ);

		// PESSOA FISICA
		d.setData(data1);
		da.atualiza(d);
		da.busca(d.getId());

		// PESSOA JURIDICA
		dJ.setData(data1);
		da.atualiza(dJ);
		da.busca(dJ.getId());

		// PESSOA FISICA
		// 16
		assertThat(d.getData()).isEqualTo(data1);
		// 17
		assertThat(d.getData()).isNotNull();
		// 18
		assertThat(d.getData()).isNotNull();

		// PESSOA JURIDICA
		// 19
		assertThat(dJ.getData()).isEqualTo(data1);
		// 20
		assertThat(dJ.getData()).isNotNull();
		// 21
		assertThat(dJ.getData()).isNotNull();
	}

	@Test
	public void testaRemoveDiariaAvulsa() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("10").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("11").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getData()).isNotNull();
		// 3
		assertThat(d.getPessoa()).isNotNull();
		// 4
		assertThat(d.getQuarto()).isNotNull();

		da.remove(d.getId());

		// 5
		assertThat(da.busca(pf.getId())).isNull();

		// PESSOA JURIDICA
		// 6
		assertThat(dJ.getId()).isNotNull();
		// 7
		assertThat(dJ.getData()).isNotNull();
		// 8
		assertThat(dJ.getPessoa()).isNotNull();
		// 9
		assertThat(dJ.getQuarto()).isNotNull();

		da.remove(dJ.getId());

		// 10
		assertThat(da.busca(pj.getId())).isNull();

	}

	@Test
	public void testaBuscaPorData() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("16").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("3").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		DiariaAvulsa diariaBD = da.buscaPorData(data).get(0);
		// 1
		assertThat(diariaBD.getData()).as("Fri Jan 05 00:10:00 BRST 2018");
		// 2
		assertThat(diariaBD.getId()).isNotNull();
		// 3
		assertThat(diariaBD.getData()).isNotNull();
		// 4
		assertThat(diariaBD.getPessoa()).isNotNull();
		// 5
		assertThat(diariaBD.getData()).isEqualToIgnoringHours(data);

		// PESSOA JURIDICA
		DiariaAvulsa diariaBD1 = da.buscaPorData(data).get(0);
		// 6
		assertThat(diariaBD1.getData()).as("Fri Jan 05 00:10:00 BRST 2018");
		// 7
		assertThat(diariaBD1.getId()).isNotNull();
		// 8
		assertThat(diariaBD1.getData()).isNotNull();
		// 9
		assertThat(diariaBD1.getPessoa()).isNotNull();
		// 10
		assertThat(diariaBD1.getData()).isEqualToIgnoringHours(data);
	}

	@Test
	public void testaBuscaPorQuarto() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		DiariaAvulsa diariaBD = da.buscaPorQuarto(quarto).get(0);
		// 1
		assertThat(diariaBD.getQuarto()).isEqualTo(quarto);
		// 2
		assertThat(diariaBD.getId()).isNotNull();
		// 3
		assertThat(diariaBD.getQuarto()).isNotNull();
		// 4
		assertThat(diariaBD.getQuarto()).isEqualTo(quarto);
		// 5
		assertThat(diariaBD.getQuarto()).isNotEqualTo(quartoJ);

		// PESSOA JURIDICA
		DiariaAvulsa diariaBD1 = da.buscaPorQuarto(quartoJ).get(0);
		// 6
		assertThat(diariaBD1.getQuarto()).isEqualTo(quartoJ);
		// 7
		assertThat(diariaBD1.getId()).isNotNull();
		// 8
		assertThat(diariaBD1.getQuarto()).isNotNull();
		// 9
		assertThat(diariaBD1.getQuarto()).isEqualTo(quartoJ);
		// 10
		assertThat(diariaBD1.getQuarto()).isNotEqualTo(quarto);
	}

	@Test
	public void testaBuscaPorPessoa() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaAvulsa d = DiariaAvulsa.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		DiariaAvulsa dJ = DiariaAvulsa.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pfd.salva(pf);
		pjd.salva(pj);

		pf.addDiariaAvulsa(d);
		d.addPessoaFisica(pf);
		d.setQuarto(quarto);

		pj.addDiariaAvulsa(dJ);
		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		DiariaAvulsa diariaBD = da.buscaPorPessoa(pf).get(0);
		// 1
		assertThat(diariaBD.getId()).isNotNull();
		// 2
		assertThat(diariaBD.getPessoa()).isNotNull();
		// 3
		assertThat(diariaBD.getPessoa()).isNotEqualTo(pj);
		// 4
		assertThat(diariaBD.getPessoa()).isEqualTo(diariaBD.getPessoa());

		// PESSOA JURIDICA
		DiariaAvulsa diariaBD1 = da.buscaPorPessoa(pj).get(0);
		// 4
		assertThat(diariaBD1.getPessoa()).isEqualTo(diariaBD1.getPessoa());
		// 5
		assertThat(diariaBD1.getId()).isNotNull();
		// 6
		assertThat(diariaBD1.getPessoa()).isNotNull();
		// 7
		assertThat(diariaBD1.getPessoa()).isNotEqualTo(pf);
	}

	@Test
	public void DiariaAvulsaAtualizaPorIdNulo() throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data1 = sdf1.parse("05-10-2018");

		DiariaAvulsa d = DiariaAvulsa.builder().data(data1).build();

		d.setId(null);
		// 1
		assertThat(d.getId()).isNull();
		da.atualiza(d);
		// 2
		assertThat(d.getId()).isNotNull();
	}
}
