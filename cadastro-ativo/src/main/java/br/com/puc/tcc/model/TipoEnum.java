package br.com.puc.tcc.model;

public enum TipoEnum {

	CORRETIVA("C"),
	PROGRAMADA("P");
	
	private String descricao;
	
	TipoEnum(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
