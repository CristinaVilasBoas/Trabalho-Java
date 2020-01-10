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
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Reserva;
import br.edu.ifrs.canoas.trabalhoJPA.pojo.Sexo;

public class ReservaDAOTest {

	ReservaDAO rd = new ReservaDAO();
	PessoaFisicaDAO pfd = new PessoaFisicaDAO();
	DiariaReservadaDAO da = new DiariaReservadaDAO();
	PessoaJuridicaDAO pjd = new PessoaJuridicaDAO();

	@Test
	public void testSalva() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("03-11-18");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(3.50).build();
		Reserva rj = Reserva.builder().data(data).valor(4.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		// 1
		assertThat(pf.getId()).isNotNull();
		// 2
		assertThat(r.getValor()).isEqualTo(3.50);
		// 3
		assertThat(r.getId()).isNotNull();
		// 4
		assertThat(r.getPessoa()).isEqualTo(pf);
		// 5
		assertThat(r.getData()).isNotNull();
		// 6
		assertThat(r.getValor()).isNotNull();
		// 7
		assertThat(r.getData()).isEqualTo(data);
		// 8
		assertThat(r.getDiariaReservada()).isEqualTo(d);
		// 9
		assertThat(r.getDiariaReservada()).isNotNull();
		// 10
		assertThat(d.getId()).isNotNull();

		// PESSOA JURIDICA
		// 11
		assertThat(pj.getId()).isNotNull();
		// 12
		assertThat(rj.getValor()).isEqualTo(4.50);
		// 13
		assertThat(rj.getId()).isNotNull();
		// 14
		assertThat(rj.getPessoa()).isEqualTo(pj);
		// 15
		assertThat(rj.getData()).isNotNull();
		// 16
		assertThat(rj.getData()).isEqualTo(data);
		// 17
		assertThat(rj.getDiariaReservada()).isEqualTo(dj);
		// 18
		assertThat(dj.getId()).isNotNull();
		// 19
		assertThat(rj.getDiariaReservada()).isNotNull();
	}

	@Test
	public void testaAtualizaReserva() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("03-11-18");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(3.50).build();
		Reserva rj = Reserva.builder().data(data).valor(3.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		// 1
		assertThat(r.getValor()).isEqualTo(3.50);
		// 2
		assertThat(r.getId()).isNotNull();
		// 3
		assertThat(r.getPessoa()).isEqualTo(pf);
		// 4
		assertThat(r.getValor()).isNotNull();
		// 5
		assertThat(r.getPessoa()).isNotNull();
		// 6
		assertThat(r.getData()).isNotNull();
		// 7
		assertThat(r.getDiariaReservada()).isNotNull();
		// 8
		assertThat(r.getDiariaReservada()).isEqualTo(d);

		// PESSOA JURIDICA
		// 9
		assertThat(rj.getValor()).isEqualTo(3.50);
		// 10
		assertThat(rj.getId()).isNotNull();
		// 11
		assertThat(rj.getPessoa()).isEqualTo(pj);
		// 12
		assertThat(rj.getValor()).isNotNull();
		// 13
		assertThat(rj.getPessoa()).isNotNull();
		// 14
		assertThat(rj.getData()).isNotNull();
		// 15
		assertThat(rj.getDiariaReservada()).isNotNull();
		// 16
		assertThat(rj.getDiariaReservada()).isEqualTo(dj);

		r.setValor(145.40);
		rj.setValor(145.40);

		rd.atualiza(r);
		rd.atualiza(rj);

		Reserva novo = rd.busca(r.getId());
		Reserva novo1 = rd.busca(rj.getId());

		// PESSOA FISICA
		// 17
		assertThat(novo.getValor()).isEqualTo(145.40);
		// 18
		assertThat(novo.getValor()).isNotNull();
		// 19
		assertThat(novo.getValor()).isNotEqualTo(3.50);
		// 20
		assertThat(novo.getId()).isNotNull();

		// PESSOA JURIDICA
		// 21
		assertThat(novo1.getValor()).isEqualTo(145.40);
		// 22
		assertThat(novo1.getValor()).isNotNull();
		// 23
		assertThat(novo1.getValor()).isNotEqualTo(3.50);
		// 24
		assertThat(novo.getId()).isNotNull();
	}

	@Test
	public void testaBuscaTodasReservas() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("03-11-18");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(31.50).build();
		Reserva rj = Reserva.builder().data(data).valor(32.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		List<Reserva> reservas = rd.busca();
		// 1
		assertThat(reservas).size().isGreaterThanOrEqualTo(2);
		// 2
		assertThat(reservas).size().isNotNull();
		// 3
		assertThat(reservas).isNotEmpty();
		// 4
		assertThat(reservas).size().isGreaterThan(1);
	}

	@Test
	public void testaPorValor() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("03-11-18");

		PessoaFisica pf = PessoaFisica.builder().nome("Pessoa").sexo(Sexo.Feminino).cpf("132456798")
				.email("pessoa@gmail.com").rg("132456798").cpf("000.000.008-00").data(data).telefone("123456798")
				.build();

		PessoaJuridica pj = PessoaJuridica.builder().nome("Pessoa Juridica").email("pessoaJuridica@gmail.com")
				.cnpj("35.773.727/0001-96").estadual("776/1743607").razao("Indústrias Ltda").telefone("(55) 3976-0371")
				.municipal("775/1743607").build();

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(3.50).build();
		Reserva rj = Reserva.builder().data(data).valor(4.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		Reserva reservaBD = rd.buscaPorValor(3.50).get(0);
		// 1
		assertThat(reservaBD.getValor()).isEqualTo(3.50);
		// 2
		assertThat(reservaBD.getId()).isNotNull();
		// 3
		assertThat(reservaBD.getValor()).isNotNull();
		// 4
		assertThat(reservaBD.getValor()).isNotEqualTo(35.20);
		// 5
		assertThat(reservaBD.getPessoa()).isNotNull();
		// 6
		assertThat(reservaBD.getValor()).isEqualByComparingTo(r.getValor());

		// PESSOA JURIDICA
		Reserva reservaBD1 = rd.buscaPorValor(4.50).get(0);
		// 7
		assertThat(reservaBD1.getValor()).isEqualTo(4.50);
		// 8
		assertThat(reservaBD1.getId()).isNotNull();
		// 9
		assertThat(reservaBD1.getValor()).isNotNull();
		// 10
		assertThat(reservaBD1.getValor()).isNotEqualTo(35.20);
		// 11
		assertThat(reservaBD1.getValor()).isEqualTo(rj.getValor());
		// 12
		assertThat(reservaBD1.getPessoa()).isNotNull();
	}

	@Test
	public void testaPorData() throws ParseException {

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

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(36.50).build();
		Reserva rj = Reserva.builder().data(data1).valor(46.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		Reserva reservaBD = rd.buscaPorData(data).get(0);
		// 1
		assertThat(reservaBD.getData()).as("Fri Jan 05 00:10:00 BRST 2018");
		// 2
		assertThat(reservaBD.getId()).isNotNull();
		// 3
		assertThat(reservaBD.getData()).isNotNull();
		// 4
		assertThat(reservaBD.getData()).isNotEqualTo(data1);
		// 5
		assertThat(reservaBD.getData()).isEqualToIgnoringHours(data);

		// PESSOA JURIDICA
		Reserva reservaBD1 = rd.buscaPorData(data).get(0);
		// 6
		assertThat(reservaBD1.getData()).as("Fri Jan 05 00:10:00 BRST 2018");
		// 7
		assertThat(reservaBD1.getId()).isNotNull();
		// 8
		assertThat(reservaBD1.getData()).isNotNull();
		// 9
		assertThat(reservaBD1.getData()).isNotEqualTo(data1);
		// 10
		assertThat(reservaBD1.getData()).isEqualToIgnoringHours(data);

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

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(36.50).build();
		Reserva rj = Reserva.builder().data(data1).valor(46.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		Reserva reservaBD = rd.buscaPorPessoa(pf).get(0);
		// 1
		assertThat(reservaBD.getId()).isNotNull();
		// 2
		assertThat(reservaBD.getData()).isNotNull();
		// 3
		assertThat(reservaBD.getPessoa()).isNotNull();
		// 4
		assertThat(reservaBD.getValor()).isNotNull();
		// 5
		assertThat(reservaBD.getPessoa()).isEqualTo(reservaBD.getPessoa());

		// PESSOA JURIDICA
		Reserva reservaBD1 = rd.buscaPorPessoa(pj).get(0);
		// 1
		assertThat(reservaBD1.getId()).isNotNull();
		// 2
		assertThat(reservaBD1.getData()).isNotNull();
		// 3
		assertThat(reservaBD1.getPessoa()).isNotNull();
		// 4
		assertThat(reservaBD1.getValor()).isNotNull();
		// 5
		assertThat(reservaBD1.getPessoa()).isEqualTo(reservaBD1.getPessoa());

	}

	@Test
	public void testaRemoveReserva() throws ParseException {
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

		DiariaReservada d = DiariaReservada.builder().data(data).build();
		DiariaReservada dj = DiariaReservada.builder().data(data).build();

		Reserva r = Reserva.builder().data(data).valor(3.50).build();
		Reserva rj = Reserva.builder().data(data1).valor(4.50).build();

		pj.addReserva(rj);
		pf.addReserva(r);

		pjd.salva(pj);
		pfd.salva(pf);

		r.setDiariaReservada(d);
		r.setPessoa(pf);

		rj.setDiariaReservada(dj);
		rj.setPessoa(pj);

		rd.salva(r);
		rd.salva(rj);

		// PESSOA FISICA
		// 1
		assertThat(pf.getId()).isNotNull();
		// 2
		assertThat(r.getValor()).isEqualTo(3.50);
		// 3
		assertThat(r.getId()).isNotNull();
		// 4
		assertThat(r.getPessoa()).isEqualTo(pf);
		// 5
		assertThat(r.getData()).isNotNull();
		// 6
		assertThat(r.getValor()).isNotNull();
		// 7
		assertThat(r.getData()).isEqualTo(data);
		// 8
		assertThat(r.getDiariaReservada()).isEqualTo(d);
		// 9
		assertThat(r.getDiariaReservada()).isNotNull();
		// 10
		assertThat(d.getId()).isNotNull();

		rd.remove(r.getId());
		// 11
		assertThat(rd.busca(r.getId())).isNull();

		// PESSOA JURIDICA
		// 12
		assertThat(pj.getId()).isNotNull();
		// 13
		assertThat(rj.getValor()).isEqualTo(4.50);
		// 14
		assertThat(rj.getId()).isNotNull();
		// 15
		assertThat(rj.getPessoa()).isEqualTo(pj);
		// 16
		assertThat(rj.getData()).isNotNull();
		// 17
		assertThat(rj.getData()).isEqualTo(data);
		// 18
		assertThat(rj.getDiariaReservada()).isEqualTo(dj);
		// 19
		assertThat(dj.getId()).isNotNull();
		// 20
		assertThat(rj.getDiariaReservada()).isNotNull();

		rd.remove(rj.getId());
		// 21
		assertThat(rd.busca(rj.getId())).isNull();
	}

	@Test
	public void testaReservaComIdNull() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date data = sdf.parse("05-10-2018");

		Reserva r = Reserva.builder().data(data).valor(3.50).build();

		r.setId(null);
		// 1
		assertThat(r.getId()).isNull();
		rd.atualiza(r);
		// 2
		assertThat(r.getId()).isNotNull();
	}
}
