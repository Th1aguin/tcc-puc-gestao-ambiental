package br.com.puc.tcc.client;


import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.puc.tcc.model.Inspecao;

public class InspecaoClient {

		
		private RestTemplate restTemplate;
		
		private String URI_BASE;
		
		private String URL;
		
		private String URN_BASE = "/monitoramento/inspecao";
		
		private String URN_BASE_BARRAGEM = "/monitoramento/barragem/";
		
		private String credencial;
		
		public InspecaoClient(String url, String usuario, String senha) {
			restTemplate = new RestTemplate();
			
			URL =url;
			
			URI_BASE = url.concat(URN_BASE);
			
			String credencialAux = usuario + ":" + senha;
			
			credencial = "Basic " + Base64.getEncoder()
					.encodeToString(credencialAux.getBytes());
		}
		
		public List<Inspecao> listar(Long search) {
			
			String path = URI_BASE;
			if(search != null){
				path = URI_BASE+"?search="+search;
			}
			RequestEntity<Void> request = RequestEntity
					.get(URI.create(path))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Inspecao[]> response = restTemplate.exchange(request, Inspecao[].class);
			
			return Arrays.asList(response.getBody());
		}
		
		public String salvar(Inspecao inspecao) {		
			RequestEntity<Inspecao> request = RequestEntity
					.post(URI.create(URL+URN_BASE_BARRAGEM+inspecao.getBarragem().getId()+"/inspecao"))
					.header("Authorization", credencial)
					.body(inspecao);
			
			ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
			
			return response.getHeaders().getLocation().toString();
		}
		
		public Inspecao buscar(Long id) {
			RequestEntity<Void> request = RequestEntity
					.get(URI.create(URI_BASE+"/"+id))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Inspecao> response = restTemplate.exchange(request, Inspecao.class);
			
			return response.getBody();
		}
		
		
		public HttpStatus deletar(Long id) {
			RequestEntity<Void> request = RequestEntity
					.delete(URI.create(URI_BASE+"/"+id))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Inspecao> response = restTemplate.exchange(request, Inspecao.class);
			
			return response.getStatusCode();
		}
	 }