package br.com.puc.tcc.model;

public enum NivelEnum {

	ALTO("Alto"),
	MEDIO("Médio"),
	BAIXO("Baixo");
	
	private String descricao;
	
	NivelEnum(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
