package br.univille.projcolabassistant.controller;

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
import br.univille.projcolabassistant.repository.CityRepository;

@Controller
@RequestMapping("/city")
public class CityController {
	@Autowired
    private CityRepository cityRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<City> listaCity = this.cityRepository.findAll();
    
        
        return new ModelAndView("city/index","listacity",listaCity);
    }
    @GetMapping("/novo")
    public String createForm(@ModelAttribute City city) {
        return "/city/form";
    }
    @PostMapping(params="form")
    public ModelAndView save(@Valid City city, BindingResult result, RedirectAttributes redirect) {
        
        city = this.cityRepository.save(city);
        
        return new ModelAndView("redirect:/city");
    }
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") City city) {
        return new ModelAndView("city/form","city",city);
    }
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.cityRepository.deleteById(id);
        return new ModelAndView("redirect:/city");
    }
}
