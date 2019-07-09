package br.com.puc.tcc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.binder.BarragemPropertyEditor;
import br.com.puc.tcc.client.BarragemClient;
import br.com.puc.tcc.client.InspecaoClient;
import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.Inspecao;
import br.com.puc.tcc.model.NivelEnum;
import br.com.puc.tcc.repository.filtro.InspecaoFiltro;


@Controller
@RequestMapping("/inspect")
public class InspecaoControler {
	
	@Value("${endereco.ws.gateway}")
    private String enderecoWs;
	
	@Value("${endereco.ws.user}")
    private String user;
	
	@Value("${endereco.ws.password}")
    private String password;
	
	public static final String CADASTRO_VIEW = "CadastroInspecao";
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		binder.registerCustomEditor(Barragem.class, new BarragemPropertyEditor(cliente));
    }
	
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
			InspecaoClient cliente = new InspecaoClient(enderecoWs, user, password);
			cliente.salvar(Inspecao);
			attr.addFlashAttribute("mensagem","Inspecao salva com sucesso");
			return "redirect:/inspect/novo";
		}catch(IllegalArgumentException e) {
			erros.rejectValue("data", null,e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisa(@ModelAttribute("filtro")  InspecaoFiltro filtro) {
		InspecaoClient cliente = new InspecaoClient(enderecoWs, user, password);
		
		List<Inspecao> lista =cliente.listar(filtro.getId());
		ModelAndView mv = new ModelAndView("PesquisaInspecao");
		mv.addObject("inpecoes",lista);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigo) {
		InspecaoClient cliente = new InspecaoClient(enderecoWs, user, password);
		Inspecao inspecao =  cliente.buscar(codigo);
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(inspecao);
		return mv;
	}
	
	@RequestMapping(value ="{codigo}",method =RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		InspecaoClient cliente = new InspecaoClient(enderecoWs, user, password);
		cliente.deletar(codigo);
		attr.addFlashAttribute("mensagem","Inspecao excluida com sucesso");
		return "redirect:/inspect/";
	}
	
	@ModelAttribute("listaBarragens")
	public List<Barragem> todasBarragens(){
		BarragemClient cliente = new BarragemClient(enderecoWs, user, password);
		List<Barragem> lista = cliente.listar(null);
		return lista;
	}
	
	@ModelAttribute("listaStatus")
	public List<NivelEnum> todosStatusTitulo(){
		return Arrays.asList(NivelEnum.values());
	}
}
