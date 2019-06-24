package br.com.puc.tcc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Inspecao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//banco gera pra gente
	private Long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull(message="Data obrigatória")
	@JsonFormat
	  (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Categoria de Risco obrigatória")
	private NivelEnum categoriaRisco;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Dano Potenciao obrigatório")
	private NivelEnum danoPotencial;
	
	@NotNull(message="Considerações obrigatória")
	private String consideracoes;
	
	@NotNull(message="Altura obrigatória")
	private Double altura;
	
	@NotNull(message="Volume obrigatório")
	private Double volume;
	
	@ManyToOne
	@JoinColumn(name = "barragem_id")
	private Barragem barragem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public NivelEnum getCategoriaRisco() {
		return categoriaRisco;
	}

	public void setCategoriaRisco(NivelEnum categoriaRisco) {
		this.categoriaRisco = categoriaRisco;
	}

	public String getConsideracoes() {
		return consideracoes;
	}

	public void setConsideracoes(String consideracoes) {
		this.consideracoes = consideracoes;
	}

	public NivelEnum getDanoPotencial() {
		return danoPotencial;
	}

	public void setDanoPotencial(NivelEnum danoPotencial) {
		this.danoPotencial = danoPotencial;
	}

	public Barragem getBarragem() {
		return barragem;
	}

	public void setBarragem(Barragem barragem) {
		this.barragem = barragem;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspecao other = (Inspecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspecao [id=" + id + ", data=" + data + ", categoriaRisco=" + categoriaRisco + ", danoPotencial="
				+ danoPotencial + ", consideracoes=" + consideracoes + "]";
	}
}
