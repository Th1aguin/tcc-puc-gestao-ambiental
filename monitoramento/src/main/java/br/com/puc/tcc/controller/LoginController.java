package br.com.puc.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Seu usuario e senha estão invalidos");

        if (logout != null)
            model.addAttribute("message", "Você logou com sucesso");

        return "login";
    }

}