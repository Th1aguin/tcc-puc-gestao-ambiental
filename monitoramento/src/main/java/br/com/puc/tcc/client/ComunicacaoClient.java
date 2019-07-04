package br.com.puc.tcc.client;

import java.net.URI;
import java.util.Base64;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ComunicacaoClient {

	
	private RestTemplate restTemplate;
	
	private String URI_BASE;
	
	private String URN_BASE = "/seguranca/comunicacao";
	
	private String credencial;
	
	public ComunicacaoClient(String url, String usuario, String senha) {
		restTemplate = new RestTemplate();
		
		URI_BASE = url.concat(URN_BASE);
		
		String credencialAux = usuario + ":" + senha;
		
		credencial = "Basic " + Base64.getEncoder()
				.encodeToString(credencialAux.getBytes());
	}
	
	public String salvar(Comunicacao comunicacao) {		
		RequestEntity<Comunicacao> request = RequestEntity
				.post(URI.create(URI_BASE))
				.header("Authorization", credencial)
				.body(comunicacao);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
 }