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
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CityRepository cityRepository;
		
	@GetMapping("")
    public ModelAndView index() {
		List<User> listUser = this.userRepository.findAll();				
		return new ModelAndView("user/index","listuser", listUser);
	}
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        
		user.setEnabled(true);
        user = this.userRepository.save(user);
        
        return new ModelAndView("redirect:/user");
    }
	
	@GetMapping("/new")
    public ModelAndView createForm(@ModelAttribute User user) {
        List<City> listCity = cityRepository.findAll();
        return new ModelAndView("user/form", "listcity", listCity);
    }
	
	@GetMapping(value="/modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") User user) {
		List<City> listCity = cityRepository.findAll();
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listcity", listCity);
		dados.put("user",user);
        return new ModelAndView("user/form", dados);
    }
	
	@GetMapping(value="remove/{id}")
    public ModelAndView remove(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.userRepository.deleteById(id);
        return new ModelAndView("redirect:/user");
    }
	
	//@GetMapping("/list")
    //public ModelAndView list(@ModelAttribute User user) {
	//	List<User> listUser = this.userRepository.findAll();
	//	return new ModelAndView("user/list", "listuser", listUser);
    //}
}
