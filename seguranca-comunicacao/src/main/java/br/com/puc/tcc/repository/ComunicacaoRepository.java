package br.com.puc.tcc.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Comunicacao;

public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {

}
