package br.com.puc.tcc.client;


import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.puc.tcc.model.Barragem;

public class BarragemClient {

		
		private RestTemplate restTemplate;
		
		private String URI_BASE;
		
		private String URN_BASE = "/monitoramento/barragem";
		
		private String credencial;
		
		public BarragemClient(String url, String usuario, String senha) {
			restTemplate = new RestTemplate();
			
			URI_BASE = url.concat(URN_BASE);
			
			String credencialAux = usuario + ":" + senha;
			
			credencial = "Basic " + Base64.getEncoder()
					.encodeToString(credencialAux.getBytes());
		}
		
		public List<Barragem> listar(String search) {
			
			String path = URI_BASE;
			if(search != null){
				path = URI_BASE+"?search="+search;
			}
			RequestEntity<Void> request = RequestEntity
					.get(URI.create(path))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Barragem[]> response = restTemplate.exchange(request, Barragem[].class);
			
			return Arrays.asList(response.getBody());
		}
		
		public String salvar(Barragem livro) {		
			RequestEntity<Barragem> request = RequestEntity
					.post(URI.create(URI_BASE))
					.header("Authorization", credencial)
					.body(livro);
			
			ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
			
			return response.getHeaders().getLocation().toString();
		}
		
		public Barragem buscar(Long id) {
			RequestEntity<Void> request = RequestEntity
					.get(URI.create(URI_BASE+"/"+id))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Barragem> response = restTemplate.exchange(request, Barragem.class);
			
			return response.getBody();
		}
		
		
		public HttpStatus deletar(Long id) {
			RequestEntity<Void> request = RequestEntity
					.delete(URI.create(URI_BASE+"/"+id))
					.header("Authorization", credencial)
					.build();
			
			ResponseEntity<Barragem> response = restTemplate.exchange(request, Barragem.class);
			
			return response.getStatusCode();
		}
	 }