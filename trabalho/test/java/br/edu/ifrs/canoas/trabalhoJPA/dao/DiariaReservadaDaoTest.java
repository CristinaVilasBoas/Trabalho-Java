package br.edu.ifrs.canoas.trabalhoJPA.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import br.edu.ifrs.canoas.trabalhoJPA.dao.DiariaReservadaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaFisicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.PessoaJuridicaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.dao.ReservaDAO;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.DiariaReservada;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaFisica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.PessoaJuridica;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Quarto;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Reserva;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Sexo;

public class DiariaReservadaDaoTest {

	ReservaDAO rd = new ReservaDAO();
	DiariaReservadaDAO da = new DiariaReservadaDAO();
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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data1).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getData()).isEqualTo(data);
		// 3
		assertThat(d.getQuarto()).isEqualTo(quarto);
		// 4
		assertThat(d.getQuarto()).isNotNull();
		// 5
		assertThat(d.getQuarto()).isNotEqualTo(quartoJ);
		// 6
		assertThat(d.getReserva()).isNotEqualTo(dJ);

		// PESSOA JURIDICA
		// 7
		assertThat(dJ.getId()).isNotNull();
		// 8
		assertThat(dJ.getData()).isEqualTo(data);
		// 9
		assertThat(dJ.getQuarto()).isEqualTo(quartoJ);
		// 10
		assertThat(dJ.getQuarto()).isNotNull();
		// 11
		assertThat(dJ.getReserva()).isNotEqualTo(r);
		// 12
		assertThat(dJ.getQuarto()).isNotEqualTo(quarto);
	}

	@Test
	public void testaAtualizaDiariaAvulsas() throws ParseException {

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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data).build();
		Quarto quartoJ = Quarto.builder().numero("3").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getQuarto()).isNotNull();
		// 3
		assertThat(d.getData()).isNotNull();
		// 4
		assertThat(d.getData()).isEqualTo(data);
		// 5
		assertThat(d.getQuarto()).isEqualTo(quarto);

		// PESSOA JURIDICA
		// 6
		assertThat(dJ.getId()).isNotNull();
		// 7
		assertThat(dJ.getQuarto()).isNotNull();
		// 8
		assertThat(dJ.getData()).isNotNull();
		// 9
		assertThat(dJ.getData()).isEqualTo(data);
		// 10
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
		// 5
		assertThat(d.getData()).isEqualTo(data1);
		// 6
		assertThat(d.getData()).isNotNull();
		// 7
		assertThat(d.getData()).isEqualToIgnoringHours(data1);

		// PESSOA JURIDICA
		// 8
		assertThat(dJ.getData()).isEqualTo(data1);
		// 9
		assertThat(dJ.getData()).isNotNull();
		// 10
		assertThat(dJ.getData()).isEqualToIgnoringHours(data1);
	}

	@Test
	public void testaRemoveDiariaReservada() throws ParseException {
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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data1).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);
		d.addPessoaFisica(pf);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		// 1
		assertThat(d.getId()).isNotNull();
		// 2
		assertThat(d.getId()).isNotEqualByComparingTo(dJ.getId());
		da.remove(d.getId());
		// 3
		assertThat(da.busca(pf.getId())).isNull();

		// PESSOA JURIDICA
		// 4
		assertThat(dJ.getId()).isNotNull();
		// 5
		assertThat(dJ.getId()).isNotEqualByComparingTo(d.getId());
		da.remove(dJ.getId());
		// 6
		assertThat(da.busca(pj.getId())).isNull();
	}

	@Test
	public void testaBuscaPorTodasAsDiariasReservadas() throws ParseException {
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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data1).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);
		d.addPessoaFisica(pf);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		List<DiariaReservada> diarias = da.buscaDiarias();

		// 1
		assertThat(diarias).size().isGreaterThan(1);
		// 2
		assertThat(diarias).size().isNotNull();
		// 3
		assertThat(diarias).size().isNotEqualTo(1);
		// 4
		assertThat(diarias).size().isGreaterThanOrEqualTo(2);

		da.remove(dJ.getId());

		// 4
		assertThat(diarias).size().isGreaterThan(0);
		// 5
		assertThat(diarias).size().isNotNull();
		// 6
		assertThat(diarias).isNotEmpty();
		// 7
		assertThat(diarias).contains(d);
		// 8
		assertThat(diarias).size().isGreaterThanOrEqualTo(1);
	}

	@Test
	public void testaBuscaPorData() throws ParseException {

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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data1).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("2").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);
		d.addPessoaFisica(pf);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		DiariaReservada reservaBD = da.buscaPorData(data).get(0);
		// 1
		assertThat(reservaBD.getData()).as("Fri Jan 05 00:10:00 BRST 2018");
		// 2
		assertThat(reservaBD.getId()).isNotNull();
		// 3
		assertThat(reservaBD.getData()).isNotNull();
		// 4
		assertThat(reservaBD.getData()).isEqualToIgnoringHours(data);

		// PESSOA JURIDICA
		DiariaReservada reservaBD1 = da.buscaPorData(data1).get(0);
		// 1
		assertThat(reservaBD1.getData()).as("Sat Jan 06 00:10:00 BRST 2018");
		// 2
		assertThat(reservaBD1.getId()).isNotNull();
		// 3
		assertThat(reservaBD1.getData()).isNotNull();
		// 4
		assertThat(reservaBD1.getData()).isEqualToIgnoringHours(data1);
	}

	@Test
	public void testaBuscaPorQuarto() throws ParseException {

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

		Reserva r = Reserva.builder().valor(145.61).data(data).build();
		DiariaReservada d = DiariaReservada.builder().data(data).build();
		Quarto quarto = Quarto.builder().numero("2").build();

		Reserva rJ = Reserva.builder().valor(145.61).data(data1).build();
		DiariaReservada dJ = DiariaReservada.builder().data(data1).build();
		Quarto quartoJ = Quarto.builder().numero("3").build();

		pf.addReserva(r);
		pj.addReserva(rJ);

		pfd.salva(pf);
		rd.salva(r);

		pjd.salva(pj);
		rd.salva(rJ);

		d.setQuarto(quarto);
		d.addPessoaFisica(pf);

		dJ.setQuarto(quartoJ);

		da.salva(d);
		da.salva(dJ);

		// PESSOA FISICA
		DiariaReservada reservaBD = da.buscaPorQuarto(quarto).get(0);
		// 1
		assertThat(reservaBD.getQuarto()).isEqualTo(quarto);
		// 2
		assertThat(reservaBD.getId()).isNotNull();
		// 3
		assertThat(reservaBD.getQuarto()).isNotNull();
		// 4
		assertThat(reservaBD.getQuarto()).isNotEqualTo(quartoJ);

		// PESSOA JURIDICA
		DiariaReservada reservaBD1 = da.buscaPorQuarto(quartoJ).get(0);
		// 5
		assertThat(reservaBD1.getQuarto()).isEqualTo(quartoJ);
		// 6
		assertThat(reservaBD1.getId()).isNotNull();
		// 7
		assertThat(reservaBD1.getQuarto()).isNotNull();
		// 8
		assertThat(reservaBD1.getQuarto()).isNotEqualTo(quarto);
	}

	@Test
	public void testaPorDiariaReservadaIdNull() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		DiariaReservada d = DiariaReservada.builder().data(data).build();

		d.setId(null);
		// 1
		assertThat(d.getId()).isNull();
		da.atualiza(d);
		// 2
		assertThat(d.getId()).isNotNull();
	}
}
