package br.com.puc.tcc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.tcc.model.Inspecao;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;
import br.com.puc.tcc.service.InspecaoService;

@RestController
@RequestMapping("/inspecao")
public class InspecaoResource {

	
	@Autowired
	private InspecaoService inspecaoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<List<Inspecao>> listar(@RequestParam(value = "search", required=false) String search) {
		List<Inspecao> lista;
		if(search ==null) {
			lista = inspecaoService.listar();
		}else {
			InspecaoFiltro filtro = new InspecaoFiltro();
			filtro.setId(Long.parseLong(search));
			lista = inspecaoService.pesquisar(filtro);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Inspecao> buscar(@PathVariable("id") Long id) {
		Inspecao entidade = inspecaoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		inspecaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}


}
