package br.univille.projcolabassistant.controller;

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

import br.univille.projcolabassistant.model.Material;
import br.univille.projcolabassistant.repository.MaterialRepository;

@Controller
@RequestMapping("/material")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MaterialController {
	
	@Autowired
    private MaterialRepository materialRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<Material> listaMaterial = this.materialRepository.findAll();
    
        return new ModelAndView("material/index","listaMaterial",listaMaterial);
    }
    
    @GetMapping("/novo")
    public String createForm(@ModelAttribute Material Material) {
        return "material/form";
    }
    
    @PostMapping(params="form")
    public ModelAndView save(@Valid Material material, BindingResult result, RedirectAttributes redirect) {
        
    	material = this.materialRepository.save(material);
        
        return new ModelAndView("redirect:/material");
    }
    
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Material material) {
    	
        return new ModelAndView("material/form","material",material);
    }
    
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.materialRepository.deleteById(id);
        
        return new ModelAndView("redirect:/material");
    }
}
