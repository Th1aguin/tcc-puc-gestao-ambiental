package br.com.puc.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.repository.BarragemRepository;
import br.com.puc.tcc.repository.filtro.BarragemFiltro;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class BarragemService {

	@Autowired
	private BarragemRepository barragemRepository;
	
	
	public List<Barragem> listar() {
		return barragemRepository.findAll();
	}	
	
	public Barragem salvar(Barragem barragem) {
		return barragemRepository.save(barragem);
	}
	
	public Barragem buscar(Long id) {
		Optional<Barragem> barragem = barragemRepository.findById(id);
		
		if(barragem.isPresent()) {
			return barragem.get();
		}
		throw new ServiceException("A Barragem não pôde ser encontrado.");
	}
	
	public void deletar(Long id) {
		try {
			barragemRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("A Barragem não pôde ser encontrado.");
		}
	}
	
	public List<Barragem> pesquisar(BarragemFiltro filtro){
		String descricao =  filtro.getDescricao() == null ?"%":filtro.getDescricao();
		return barragemRepository.findByNomeContaining(descricao);
		
	}
	
	public void atualizar(Barragem barragem) {
		buscar(barragem.getId());
		barragemRepository.save(barragem);
	}
}
