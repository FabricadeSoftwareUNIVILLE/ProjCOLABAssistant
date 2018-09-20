package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
@RequestMapping("/institution")
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class InstitutionController {
	
    @Autowired
    private CityRepository cityRepository;
	@Autowired
	private InstitutionRepository InstitutionRepository;
	List<Institution> listInstitution = new ArrayList<Institution>();
	

	@GetMapping("")
	public ModelAndView index() {
		List<Institution> listInstitution = this.InstitutionRepository.findAll();

		return new ModelAndView("institution/index","listainst",listInstitution);
	}
	
	@GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Institution institution) {
		List<City> listaCidades = cityRepository.findAll();
		
		return new ModelAndView("institution/form","listacidades",listaCidades);
    }
	
    @PostMapping(params="form")
    public ModelAndView save(@Valid Institution institution, BindingResult result, RedirectAttributes redirect) {
        
    	institution = this.InstitutionRepository.save(institution);
        
        return new ModelAndView("redirect:/institution");
    }
    
    @GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Institution institution) {
        List<City> listaCidades = cityRepository.findAll();
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("institution",institution);
        dados.put("listacidades",listaCidades);
        
        return new ModelAndView("institution/form",dados);
    }
    
    
    
    
    @GetMapping(value="remover/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.InstitutionRepository.deleteById(id);
        return new ModelAndView("redirect:/institution");
    }

    

}