package br.com.puc.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.client.Comunicacao;
import br.com.puc.tcc.client.ComunicacaoClient;


@Controller
@RequestMapping({"/evacuacao"})
public class EvacuacaoControler {
	
	@RequestMapping
	public String pesquisa() {
		return "Evacuacao";
	}
	
	@RequestMapping(value ="{codigo}", method =RequestMethod.POST)
    public String welcome(@PathVariable Long codigo, String usuario, RedirectAttributes attr) {
        
		// todo 
		ComunicacaoClient cliente = 
				new ComunicacaoClient("http://localhost:5000", "algaworks", "s3nh4");
		
		Comunicacao comunicacao = new Comunicacao();
		comunicacao.setBarragem(codigo);
		comunicacao.setUsuario(usuario);
		cliente.salvar(comunicacao);
		
		attr.addFlashAttribute("mensagem","Processo de evacuacao iniciado com sucesso");
		return "redirect:/evacuacao/";
    }

}
