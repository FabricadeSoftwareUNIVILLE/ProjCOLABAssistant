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

@Controller
public class InstituicaoController {
	@Autowired
	private InstitutionRepository InstituicaoRepository;
	List<Institution> listaInstituicao = new ArrayList<Institution>();
	@RequestMapping("/instituicao")

	@GetMapping("")
	public ModelAndView index() {


		List<Institution> listaInstituicao = this.InstituicaoRepository.findAll();

		return new ModelAndView("instituicao/index","listainst",listaInstituicao);
	}
	
	@GetMapping("/novo")
    public String createForm(@ModelAttribute Institution  instituicao) {
        return "instituicao/form";
    }
	


}