package br.com.puc.tcc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.client.BarragemClient;
import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.MetodoEnum;
import br.com.puc.tcc.repository.filtro.BarragemFiltro;


@Controller
@RequestMapping("/dam")
public class BarragemControler {
	
	public static final String CADASTRO_VIEW = "CadastroBarragem";
	
	@Value("${endereco.ws.gateway}")
    private String enderecoWs;
	
	@Value("${endereco.ws.user}")
    private String user;
	
	@Value("${endereco.ws.password}")
    private String password;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Barragem());
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST)
	public String salvar(@Validated Barragem barragem, Errors erros, RedirectAttributes attr) {
		
		if(erros.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
			cliente.salvar(barragem);
			attr.addFlashAttribute("mensagem","Barragem salva com sucesso");
			return "redirect:/dam/novo";
		}catch(IllegalArgumentException e) {
			erros.rejectValue("data", null,e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisa(@ModelAttribute("filtro")  BarragemFiltro filtro) {
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		List<Barragem> lista = cliente.listar(filtro.getDescricao());
		ModelAndView mv = new ModelAndView("PesquisaBarragem");
		mv.addObject("barragens",lista);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigo ) {
		//magia do spring ja faz o find
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		Barragem barragem =  cliente.buscar(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(barragem);
		return mv;
	}
	
	@RequestMapping(value ="{codigo}",method =RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		cliente.deletar(codigo);
		attr.addFlashAttribute("mensagem","Barragem excluida com sucesso");
		return "redirect:/dam/";
	}
	
	@ModelAttribute("listaStatus")
	public List<MetodoEnum> todosStatusTitulo(){
		return Arrays.asList(MetodoEnum.values());
	}
}
