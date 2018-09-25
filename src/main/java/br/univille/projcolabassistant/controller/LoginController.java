package br.univille.projcolabassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.Login;

//import java.util.Scanner;

@Controller
//@RequestMapping("/login")
public class LoginController {
	
//	Scanner input = new Scanner(System.in);
	
//	@GetMapping("")
//	public ModelAndView login() {
//		
//		Login login = new Login();
//		
//		
//	}
	
	@GetMapping("/login")
    public String createForm(@ModelAttribute Login login) {
        return "login/form";
    }
	
	
	
	

}
