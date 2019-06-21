package br.com.puc.tcc.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEnum {

	CORRETIVA("C"),
	PROGRAMADA("P");
	
	private String descricao;
	
	TipoEnum(String descricao){
		this.descricao =descricao;
	}
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
}
