package br.com.puc.tcc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Pessoa;
import br.com.puc.tcc.repository.PessoaRepository;
import br.com.puc.tcc.service.exception.ServiceException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	
	public List<Pessoa> listar() {
		return repository.findAll();
	}	
	
	public Pessoa salvar(Pessoa pessoa) {
		if(pessoa.getId() != null) {
			Optional<Pessoa> a = repository.findById(pessoa.getId());
			
			if(a.isPresent()) {
				throw new ServiceException("O Pessoa já existe.");
			}
		}
		return repository.save(pessoa);
	}
	
	public Pessoa buscar(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);
		
		if(pessoa.isPresent()) {
			return pessoa.get();
		}
		throw new ServiceException("O Pessoa não pôde ser encontrado.");
	}
	
	public List<Pessoa> buscarPorBarragem(Long id) {
		List<Pessoa> lista = repository.findByBarragem(id);
		return lista;
	}
	
	public void deletar(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Pessoa pessoa) {
		buscar(pessoa.getId());
		repository.save(pessoa);
	}
}
