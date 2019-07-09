package br.com.puc.tcc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Barragem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//banco gera pra gente
	private Long id;
	
	private String nome;
	
	private Double latitude;
	
	private Double longitude;
	
	private String minerio;
	
	@Enumerated(EnumType.STRING)
	private MetodoEnum metodo;
	
	@OneToMany(mappedBy = "barragem")
	@JsonIgnore
	private List<Inspecao> solicitacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public MetodoEnum getMetodo() {
		return metodo;
	}

	public void setMetodo(MetodoEnum metodo) {
		this.metodo = metodo;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getMinerio() {
		return minerio;
	}

	public void setMinerio(String minerio) {
		this.minerio = minerio;
	}

	public List<Inspecao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Inspecao> solicitacoes) {
		this.solicitacoes = solicitacoes;
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
		Barragem other = (Barragem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Barragem [id=" + id + ", nome=" + nome + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", mineiro=" + minerio + ", metodo=" + metodo + "]";
	}
	
}
