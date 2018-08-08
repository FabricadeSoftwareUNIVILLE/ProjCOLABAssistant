package br.univille.projcolabassistant.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private CityRepository cityRepository;

	@GetMapping("")
	public ModelAndView index(@ModelAttribute User user) {
		List<City> listaCidades = cityRepository.findAll();
        return new ModelAndView("user/register","listacidades",listaCidades);
	}
	
	@GetMapping("/register")
    public ModelAndView createForm1(@ModelAttribute User user) {
        List<City> listaCidades = cityRepository.findAll();
        return new ModelAndView("user/register","listacidades",listaCidades);
    }
	
	@PostMapping(params = "form")
	public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		user.setType("Terapeuta");
		user = this.userRepository.save(user);
		return new ModelAndView("redirect:/catalogo");
		//return new ModelAndView("redirect:/"); Futuramente iremos colocar esta rota para o index
	}

	
}
