package br.com.puc.tcc.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.model.Comunicacao;
import br.com.puc.tcc.model.Pessoa;
import br.com.puc.tcc.repository.ComunicacaoRepository;
import br.com.puc.tcc.util.EnviaEmail;
import br.com.puc.tcc.util.EnviaSMS;

@Service
public class ComunicacaoService {

	
	@Autowired
	private PessoaService service;
	
	Logger logger = LoggerFactory.getLogger(ComunicacaoService.class);
	
	@Autowired
	private ComunicacaoRepository repository;
	
	public Comunicacao salvar(Comunicacao comunicacao) {
		List<Pessoa> lista = service.buscarPorBarragem(comunicacao.getBarragem());
		for(Pessoa p : lista) {
			enviarEmail(p.getEmail());
			//enviarSMS(p.getTelefone());
		}
		comunicacao.setData(new Date());
		return repository.save(comunicacao);
	}
	
	public void enviarEmail(String email) {
		try {
			EnviaEmail.send("thiagoalmeida89@yahoo.com.br", email, "Alerta de Evacuação", "Alerta de Evacuação -  favor procurar um local seguro");
		} catch (Exception e) {
			logger.error("erro enviando email",e);
		}
	}
	
	public void enviarSMS(String fone) {
		try {
			if(!fone.contains("+55")) {
				fone = "+55"+fone;
			}
			EnviaSMS.send("Alerta de Evacuação - favor procurar um local seguro",fone);
		} catch (Exception e) {
			logger.error("erro enviando sms",e);
		}
	}
	
	
}
