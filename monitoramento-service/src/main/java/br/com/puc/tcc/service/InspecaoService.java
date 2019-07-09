package br.com.puc.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Inspecao;
import br.com.puc.tcc.repository.InspecaoRepository;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class InspecaoService {

	@Autowired
	private InspecaoRepository solicitacaoRepository;
	
	
	public List<Inspecao> listar() {
		return solicitacaoRepository.findAll();
	}	
	
	public Inspecao salvar(Inspecao s) {
		return solicitacaoRepository.save(s);
	}
	
	public Inspecao buscar(Long id) {
		Optional<Inspecao> s = solicitacaoRepository.findById(id);
		
		if(s.isPresent()) {
			return s.get();
		}
		throw new ServiceException("O Solicitacao não pôde ser encontrado.");
	}
	
	public void deletar(Long id) {
		try {
			solicitacaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("O livro não pôde ser encontrado.");
		}
	}
	
	public List<Inspecao> pesquisar(InspecaoFiltro filtro){
		return solicitacaoRepository.findByBarragem_Id(filtro.getId());
		
	}
	
	public void atualizar(Inspecao livro) {
		buscar(livro.getId());
		solicitacaoRepository.save(livro);
	}
}
