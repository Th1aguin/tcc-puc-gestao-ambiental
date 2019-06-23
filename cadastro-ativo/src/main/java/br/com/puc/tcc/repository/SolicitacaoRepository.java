package br.com.puc.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
	public List<Solicitacao> findByAtivo_Codigo(Long id	);

}
