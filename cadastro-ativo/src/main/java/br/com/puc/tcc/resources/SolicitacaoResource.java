package br.com.puc.tcc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.tcc.model.Solicitacao;
import br.com.puc.tcc.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoResource {

	
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Solicitacao>> listar() {
		List<Solicitacao> lista = solicitacaoService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Solicitacao> buscar(@PathVariable("id") Long id) {
		Solicitacao entidade = solicitacaoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		solicitacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}


}
