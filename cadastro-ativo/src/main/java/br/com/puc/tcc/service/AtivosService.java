package br.com.puc.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Ativo;
import br.com.puc.tcc.repository.AtivoRepository;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class AtivosService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	
	public List<Ativo> listar() {
		return ativoRepository.findAll();
	}	
	
	public Ativo salvar(Ativo ativo) {
		if(ativo.getCodigo() != null) {
			Optional<Ativo> a = ativoRepository.findById(ativo.getCodigo());
			
			if(a.isPresent()) {
				throw new ServiceException("O Ativo já existe.");
			}
		}
		return ativoRepository.save(ativo);
	}
	
	public Ativo buscar(Long id) {
		Optional<Ativo> ativo = ativoRepository.findById(id);
		
		if(ativo.isPresent()) {
			return ativo.get();
		}
		throw new ServiceException("O Ativo não pôde ser encontrado.");
	}
}
