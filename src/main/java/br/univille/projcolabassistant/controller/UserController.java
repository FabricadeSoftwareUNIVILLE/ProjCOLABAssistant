package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.UserRepository;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CityRepository cityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private InstitutionRepository institutionRepository;
	@GetMapping("")
    public ModelAndView index() {
		List<User> listUser = this.userRepository.findAll();				
		return new ModelAndView("user/index","listuser", listUser);
	}
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        Optional<User> oldUser = this.userRepository.findById(user.getId());
		if(oldUser.isPresent())
			user.setPassword(oldUser.get().getPassword());
		user = this.userRepository.save(user);
        return new ModelAndView("redirect:/user");
    }
	
	@GetMapping("/new")
    public ModelAndView createForm(@ModelAttribute User user) {
        List<City> listCity = cityRepository.findAll();
        List<Institution> listInstitution = institutionRepository.findAll();
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("listcity", listCity);
        dados.put("listInstitution", listInstitution);
        
        return new ModelAndView("user/form", dados);
    }
	
	@GetMapping(value="/modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") User user) {
		List<City> listCity = cityRepository.findAll();
		List<Institution> listInstitution = institutionRepository.findAll();
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listcity", listCity);
		dados.put("user",user);
		dados.put("listInstitution", listInstitution);
        return new ModelAndView("user/form", dados);
    }
	
	@GetMapping(value="/modifypassword/{id}")
    public ModelAndView modifyPasswordForm(@PathVariable("id") User user) {
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("user",user);
        return new ModelAndView("user/formpassword", dados);
    }
	
	@PostMapping(params="form",value="/savepassword")
    public ModelAndView savePassword(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        Optional<User> oldUserOpt = this.userRepository.findById(user.getId());
		if(oldUserOpt.isPresent()) {
			User oldUser = oldUserOpt.get();
			oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
			this.userRepository.save(oldUser);
		}
        return new ModelAndView("redirect:/user");
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
