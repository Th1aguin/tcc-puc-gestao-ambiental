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

import br.com.puc.tcc.model.Pessoa;
import br.com.puc.tcc.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	
	@Autowired
	private PessoaService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> ativos = service.listar();
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Pessoa ativos) {
		ativos = service.salvar(ativos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ativos.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscar(@PathVariable("id") Long id) {
		Pessoa ativo = service.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Pessoa ativo, 
			@PathVariable("id") Long id) {
		ativo.setId(id);
		service.atualizar(ativo);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
