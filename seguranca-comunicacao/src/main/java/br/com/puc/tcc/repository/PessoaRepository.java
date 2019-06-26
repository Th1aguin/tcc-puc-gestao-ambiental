package br.com.puc.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	public List<Pessoa> findByBarragem(Long id);

}
