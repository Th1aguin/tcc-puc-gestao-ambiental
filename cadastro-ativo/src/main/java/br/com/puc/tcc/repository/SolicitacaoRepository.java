package br.com.puc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

}
