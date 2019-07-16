package br.com.puc.tcc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.puc.tcc.model.Comunicacao;
import br.com.puc.tcc.service.ComunicacaoService;

@RestController
@RequestMapping("/comunicacao")
public class ComunicacaoResource {

	
	@Autowired
	private ComunicacaoService service;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Comunicacao comunicacao) {
		comunicacao = service.salvar(comunicacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(comunicacao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<List<Comunicacao>> listar() {
		List<Comunicacao> ativos = service.listar();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
}
