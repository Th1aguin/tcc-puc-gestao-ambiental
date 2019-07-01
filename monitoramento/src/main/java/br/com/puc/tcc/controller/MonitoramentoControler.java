package br.com.puc.tcc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.MetodoEnum;
import br.com.puc.tcc.repository.filtro.BarragemFiltro;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;
import br.com.puc.tcc.service.BarragemService;


@Controller
@RequestMapping({"/", "/welcome"})
public class MonitoramentoControler {
	
	public static final String CADASTRO_VIEW = "CadastroBarragem";
	
	@Autowired
	private BarragemService barragemService;
	
	@RequestMapping
    public String welcome(@ModelAttribute("filtro")  InspecaoFiltro filtro, Authentication authentication) {
		
		authentication.getAuthorities();
        return "welcome";
    }

	
	@ModelAttribute("listaBarragens")
	public List<Barragem> todasBarragens(){
		return barragemService.listar();
	}
}
