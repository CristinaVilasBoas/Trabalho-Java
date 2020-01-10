package br.edu.ifrs.canoas.trabalhoJPA.pojo;

public enum Sexo {
	Masculino("M"),Feminino("F");
	
	private String valor;
	private Sexo(String valor) {
		this.valor = valor;
	}
}
