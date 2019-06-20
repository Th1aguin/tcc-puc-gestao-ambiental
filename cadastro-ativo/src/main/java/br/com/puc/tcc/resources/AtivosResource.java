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
import br.com.puc.tcc.service.AtivosService;

@RestController
@RequestMapping("/ativos")
public class AtivosResource {

	
	@Autowired
	private AtivosService ativosService;
	
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
}
