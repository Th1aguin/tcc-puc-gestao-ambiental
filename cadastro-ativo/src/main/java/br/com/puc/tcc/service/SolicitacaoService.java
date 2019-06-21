package br.com.puc.tcc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Solicitacao;
import br.com.puc.tcc.model.StatusEnum;
import br.com.puc.tcc.repository.SolicitacaoRepository;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	
	public List<Solicitacao> listar() {
		return solicitacaoRepository.findAll();
	}	
	
	public Solicitacao salvar(Solicitacao s) {
		s.setDataSolicitacao(new Date());
		s.setStatus(StatusEnum.ABERTA);
		return solicitacaoRepository.save(s);
	}
	
	public Solicitacao buscar(Long id) {
		Optional<Solicitacao> s = solicitacaoRepository.findById(id);
		
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
	
	public void atualizar(Solicitacao livro) {
		buscar(livro.getCodigo());
		solicitacaoRepository.save(livro);
	}
}
