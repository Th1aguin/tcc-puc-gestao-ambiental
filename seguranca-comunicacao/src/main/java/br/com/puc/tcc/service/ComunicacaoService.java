package br.com.puc.tcc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Comunicacao;
import br.com.puc.tcc.model.Pessoa;
import br.com.puc.tcc.queue.EmailSender;
import br.com.puc.tcc.repository.ComunicacaoRepository;

@Service
public class ComunicacaoService {

	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private EmailSender senderQueue;
	
	@Autowired
	private ComunicacaoRepository repository;
	
	public List<Comunicacao> listar() {
		return repository.findAll();
	}	
	
	public Comunicacao salvar(Comunicacao comunicacao) {
		List<Pessoa> lista = service.buscarPorBarragem(comunicacao.getBarragem());
		for(Pessoa p : lista) {
			senderQueue.send(p.getNome()+" <"+p.getEmail()+">");
		}
		comunicacao.setData(new Date());
		return repository.save(comunicacao);
	}
}
