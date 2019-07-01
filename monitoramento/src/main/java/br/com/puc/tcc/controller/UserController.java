package br.com.puc.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.puc.tcc.model.Barragem;
import br.com.puc.tcc.model.Role;
import br.com.puc.tcc.model.User;
import br.com.puc.tcc.service.SecurityService;
import br.com.puc.tcc.service.UserService;
import br.com.puc.tcc.validator.UserValidator;

@Controller
@RequestMapping("/registration")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    public static final String CADASTRO_VIEW = "registration";

    @GetMapping("/novo")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new User());
		return mv;
    }
    
    @RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") User user) {
		//magia do spring ja faz o find
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(user);
		return mv;
	}

    @RequestMapping(method =RequestMethod.POST)
    public String registration(User user, BindingResult bindingResult, RedirectAttributes attr  ) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
        	return CADASTRO_VIEW;
        }

        userService.save(user);
        //securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        attr.addFlashAttribute("mensagem","Usuario salvo com sucesso");
		return "redirect:/registration/novo";
    }
    
    @RequestMapping
	public ModelAndView pesquisa() {
		List<User> lista =userService.listar();
		ModelAndView mv = new ModelAndView("PesquisaUser");
		mv.addObject("usuarios",lista);
		return mv;
	}
    
    @RequestMapping(value ="{codigo}",method =RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
    	userService.deletar(codigo);
		attr.addFlashAttribute("mensagem","Usuario excluido com sucesso");
		return "redirect:/registration/";
	}
    
    @ModelAttribute("listaRoles")
	public List<Role> todosStatusTitulo(){
		return userService.listarRoles();
	}

}