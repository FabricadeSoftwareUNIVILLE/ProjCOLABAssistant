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
@RequestMapping("/user")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private CityRepository cityRepository;

	@GetMapping("")
	public ModelAndView index() {
		List<User> listaUser = this.userRepository.findAll();

		return new ModelAndView("user/index", "listaUs", listaUser);
	}
	
	@GetMapping("/novo")
    public ModelAndView createForm1(@ModelAttribute User user) {
        List<City> listaCidades = cityRepository.findAll();
        return new ModelAndView("user/form","listacidades",listaCidades);
    }

	@GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm1(@PathVariable("id") User user) {
        List<City> listaCidades = cityRepository.findAll();
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("user",user);
        dados.put("listacidades",listaCidades);
        
        return new ModelAndView("user/form",dados);
    }

	@PostMapping(params = "form")
	public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
		user.setType("Terapeuta");
		user = this.userRepository.save(user);
		return new ModelAndView("redirect:/user");
	}


	@GetMapping(value = "remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirect) {
		this.userRepository.deleteById(id);
		return new ModelAndView("redirect:/user");
	}
}
