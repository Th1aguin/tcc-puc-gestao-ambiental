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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.Inspecao;
import br.com.puc.tcc.repository.filtro.BarragemFiltro;
import br.com.puc.tcc.service.BarragemService;
import br.com.puc.tcc.service.InspecaoService;

@RestController
@RequestMapping("/barragem")
public class BarragemResource {

	
	@Autowired
	private BarragemService ativosService;
	@Autowired
	private InspecaoService solicitacaoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<List<Barragem>> listar(@RequestParam(value = "search", required=false) String search) {
		List<Barragem> ativos;
		if(search ==null) {
			ativos = ativosService.listar();
		}else {
			BarragemFiltro filtro = new BarragemFiltro();
			filtro.setDescricao(search);
			ativos = ativosService.pesquisar(filtro);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ativos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Barragem ativos) {
		ativos = ativosService.salvar(ativos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ativos.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Barragem> buscar(@PathVariable("id") Long id) {
		Barragem ativo = ativosService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(ativo);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		ativosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Barragem ativo, 
			@PathVariable("id") Long id) {
		ativo.setId(id);
		ativosService.atualizar(ativo);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/inspecao", method = RequestMethod.POST)
	public ResponseEntity<Void> salvarSolicitacao(@Valid @RequestBody Inspecao solicitacao,
			@PathVariable("id") Long id ) {
		solicitacao.setBarragem(ativosService.buscar(id));
		solicitacao = solicitacaoService.salvar(solicitacao);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/inspecao/{id}").buildAndExpand(solicitacao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/inspecao/{idSolicitacao}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarSolicitacao(@RequestBody Inspecao solicitacao, 
			@PathVariable("id") Long id, @PathVariable("idSolicitacao") Long idSolicitacao) {
		solicitacao.setBarragem(ativosService.buscar(id));
		solicitacao.setId(idSolicitacao);
		solicitacaoService.atualizar(solicitacao);
		
		return ResponseEntity.noContent().build();
	}
}
