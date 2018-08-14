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

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;

@Controller
@RequestMapping("/assistiveaccessory")
public class AssistiveAccessoryController {
	@Autowired
    private AssistiveAccessoryRepository accessoryRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<AssistiveAccessory> accessoriesList = this.accessoryRepository.findAll();
        
        return new ModelAndView("assistiveaccessory/index","assistiveaccessories",accessoriesList);
    }
    @GetMapping("/new")
    public String createForm(@ModelAttribute AssistiveAccessory assistiveaccessory) {
        return "assistiveaccessory/form";
    }
    @PostMapping(params="form")
    public ModelAndView save(@Valid AssistiveAccessory assistiveaccessory, BindingResult result, RedirectAttributes redirect) {
        
    	assistiveaccessory = this.accessoryRepository.save(assistiveaccessory);
        
        return new ModelAndView("redirect:/assistiveaccessory");
    }
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") AssistiveAccessory assistiveaccessory) {
        return new ModelAndView("assistiveaccessory/form","assistivecategory",assistiveaccessory);
    }
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.accessoryRepository.deleteById(id);
        return new ModelAndView("redirect:/assistiveaccessory");
    }
}
