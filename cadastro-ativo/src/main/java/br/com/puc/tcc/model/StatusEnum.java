package br.com.puc.tcc.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {

	ABERTA("A"),
	FECHADA("F");
	
	private String descricao;
	
	StatusEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
