package br.com.puc.tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.Inspecao;

public interface InspecaoRepository extends JpaRepository<Inspecao, Long> {
	
	public List<Inspecao> findByBarragem_Id(Long id	);
	public List<Inspecao> findByBarragem(Barragem barragem);

}
