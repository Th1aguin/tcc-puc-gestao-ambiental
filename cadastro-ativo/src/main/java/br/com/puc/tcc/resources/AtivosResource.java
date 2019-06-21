package br.com.puc.tcc.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.puc.tcc.model.Ativo;
import br.com.puc.tcc.model.Solicitacao;
import br.com.puc.tcc.service.AtivosService;
import br.com.puc.tcc.service.SolicitacaoService;

@RestController
@RequestMapping("/ativos")
public class AtivosResource {

	
	@Autowired
	private AtivosService ativosService;
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Ativo>> listar() {
		List<Ativo> ativos = ativosService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Ativo ativos) {
		ativos = ativosService.salvar(ativos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ativos.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ativo> buscar(@PathVariable("id") Long id) {
		Ativo ativo = ativosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		ativosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Ativo ativo, 
			@PathVariable("id") Long id) {
		ativo.setCodigo(id);
		ativosService.atualizar(ativo);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/solicitacao", method = RequestMethod.POST)
	public ResponseEntity<Void> salvarSolicitacao(@Valid @RequestBody Solicitacao solicitacao,
			@PathVariable("id") Long id ) {
		solicitacao.setAtivo(ativosService.buscar(id));
		solicitacao = solicitacaoService.salvar(solicitacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/solicitacao/{id}").buildAndExpand(solicitacao.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/solicitacao/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarSolicitacao(@RequestBody Solicitacao solicitacao, 
			@PathVariable("id") Long id, @PathVariable("idSolicitacao") Long idSolicitacao) {
		solicitacao.setAtivo(ativosService.buscar(id));
		solicitacao.setCodigo(idSolicitacao);
		solicitacaoService.atualizar(solicitacao);
		
		return ResponseEntity.noContent().build();
	}
}
