package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.InstitutionRepository;
@RequestMapping("/Institution")
@Controller
public class InstitutionController {
	@Autowired
	private InstitutionRepository InstitutionRepository;
	List<Institution> listInstitution = new ArrayList<Institution>();
	

	@GetMapping("")
	public ModelAndView index() {
		List<Institution> listInstitution = this.InstitutionRepository.findAll();

		return new ModelAndView("Institution/index","listainst",listInstitution);
	}
	
	@GetMapping("/novo")
    public String createForm(@ModelAttribute Institution Institution) {
		return "Institution/form";
    }

}