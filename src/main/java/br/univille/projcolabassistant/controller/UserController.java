package br.univille.projcolabassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UserController {
	
	
	@GetMapping("")
    public ModelAndView index() {
		
		
		return new ModelAndView("user/index");
	}
	
	

}
