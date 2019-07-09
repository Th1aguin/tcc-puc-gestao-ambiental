package br.com.puc.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.puc.tcc.client.BarragemClient;
import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;


@Controller
@RequestMapping({"/", "/welcome"})
public class MonitoramentoControler {
	
	public static final String CADASTRO_VIEW = "CadastroBarragem";
	
	@Value("${endereco.ws.gateway}")
    private String enderecoWs;
	
	@Value("${endereco.ws.user}")
    private String user;
	
	@Value("${endereco.ws.password}")
    private String password;
	
	@RequestMapping
    public String welcome(@ModelAttribute("filtro")  InspecaoFiltro filtro, Authentication authentication) {
		
		authentication.getAuthorities();
        return "welcome";
    }

	
	@ModelAttribute("listaBarragens")
	public List<Barragem> todasBarragens(){
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		List<Barragem> lista = cliente.listar(null);
		return lista;
	}
}
