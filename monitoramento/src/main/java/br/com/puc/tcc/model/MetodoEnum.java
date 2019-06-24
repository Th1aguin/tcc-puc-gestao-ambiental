package br.com.puc.tcc.model;

public enum MetodoEnum {

	ETAPA_UNICA("Etapa única"),
	ALTEAMENTO_JUSANTE("Alteamento a jusante"),
	ALTEAMENTO_MONTANTE("Alteamento a montante"),
	DESCONHECIDO("Desconhecido");
	
	private String descricao;
	
	MetodoEnum(String descricao){
		this.descricao =descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
