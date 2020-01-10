package br.edu.ifrs.canoas.trabalhoJPA.pojo;

public enum TipoDeQuarto {
	STANDART("STANDART"), DUPLO("DUPLO"), TRIPLO("TRIPLO"), MASTER("MASTER"),
	PRESIDENCIAL("PRESIDENCIAL"), MEGA_ULTRA("MEGA_ULTRA");

	private String valor;

	private TipoDeQuarto(String valor) {
		this.valor = valor;
	}
}