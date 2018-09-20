package br.univille.projcolabassistant.controller;

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

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;

@Controller
@RequestMapping("/accessorycolor")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AccessoryColorController {
	@Autowired
    private AccessoryColorRepository accessorycolorRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<AccessoryColor> listaccessorycolor = this.accessorycolorRepository.findAll();

        
        return new ModelAndView("accessorycolor/index","listaccessorycolor",listaccessorycolor);
    }
    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute AccessoryColor accessorycolor) {
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("accessorycolor",accessorycolor);
        
        return new ModelAndView("accessorycolor/form",dados);
    }

    
    
    @PostMapping(params="form")
    public ModelAndView save(@Valid AccessoryColor accessorycolor, BindingResult result, RedirectAttributes redirect) {
        
    	accessorycolor = this.accessorycolorRepository.save(accessorycolor);
        
        return new ModelAndView("redirect:/accessorycolor");
    }
    
    
    
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") AccessoryColor accessorycolor) {
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("accessorycolor",accessorycolor);
        return new ModelAndView("accessorycolor/form",dados);
    }
    
    
    
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.accessorycolorRepository.deleteById(id);
        return new ModelAndView("redirect:/accessorycolor");
    }
}
