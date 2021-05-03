package br.com.foursys.locadora.util;

public enum Perfil {
	
	ADMIN("Administrador"),
	DEV("Desenvolvedor"),
	USER("Usuario");

	private String descricao;
	
	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
