package br.com.puc.tcc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroControler implements ErrorController  {
 
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
	   
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
		ModelAndView mv = new ModelAndView("error");
		
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	mv.addObject("mensagem","Pagina não encontrada");
	        }
	        if(statusCode == HttpStatus.FORBIDDEN.value()) {
	        	mv.addObject("mensagem","Não autorizado");
	        }
	        if(statusCode == HttpStatus.GATEWAY_TIMEOUT.value()) {
	        	mv.addObject("mensagem","Gateway timeout");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	mv.addObject("mensagem","Erro no servidor tente novamente mais tarde");
	        }
	    }
	    return mv;
	}
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
