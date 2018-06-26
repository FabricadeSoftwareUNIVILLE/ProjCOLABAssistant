package br.univille.projcolabassistant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	
	
	@GetMapping("")
    public ModelAndView index() {
		List<User> listUser = this.userRepository.findAll();
		
		return new ModelAndView("user/index", "listuser", listUser);
	}
	
	@GetMapping("/new")
    public String createForm(@ModelAttribute User user) {
        return "user/form";
    }
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        
        user = this.userRepository.save(user);
        
        return new ModelAndView("redirect:/user");
    }
	
}
