package br.com.puc.tcc.model;

public enum StatusEnum {

	ABERTA("A"),
	FECHADA("F");
	
	private String descricao;
	
	StatusEnum(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
