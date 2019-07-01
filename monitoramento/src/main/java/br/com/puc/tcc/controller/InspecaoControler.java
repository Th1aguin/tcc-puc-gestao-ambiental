package br.com.puc.tcc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.Inspecao;
import br.com.puc.tcc.model.NivelEnum;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;
import br.com.puc.tcc.service.BarragemService;
import br.com.puc.tcc.service.InspecaoService;


@Controller
@RequestMapping("/inspect")
public class InspecaoControler {
	
	public static final String CADASTRO_VIEW = "CadastroInspecao";
	
	@Autowired
	private InspecaoService service;
	
	@Autowired
	private BarragemService barragemService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Inspecao());
		return mv;
	}
	
	@RequestMapping(method =RequestMethod.POST)
	public String salvar(@Validated Inspecao Inspecao, Errors erros, RedirectAttributes attr) {
		
		if(erros.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			service.salvar(Inspecao);
			attr.addFlashAttribute("mensagem","Inspecao salva com sucesso");
			return "redirect:/inspect/novo";
		}catch(IllegalArgumentException e) {
			erros.rejectValue("data", null,e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisa(@ModelAttribute("filtro")  InspecaoFiltro filtro) {
		List<Inspecao> lista =service.pesquisar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaInspecao");
		mv.addObject("inpecoes",lista);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Inspecao titulo) {
		//magia do spring ja faz o find
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value ="{codigo}",method =RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		service.deletar(codigo);
		attr.addFlashAttribute("mensagem","Inspecao excluida com sucesso");
		return "redirect:/inspect/";
	}
	
	@ModelAttribute("listaBarragens")
	public List<Barragem> todasBarragens(){
		return barragemService.listar();
	}
	
	@ModelAttribute("listaStatus")
	public List<NivelEnum> todosStatusTitulo(){
		return Arrays.asList(NivelEnum.values());
	}
}
