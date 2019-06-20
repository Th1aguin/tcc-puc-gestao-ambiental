package br.com.puc.tcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long> {

}
