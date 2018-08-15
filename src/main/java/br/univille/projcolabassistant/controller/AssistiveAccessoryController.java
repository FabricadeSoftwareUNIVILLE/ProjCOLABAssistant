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

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;

@Controller
@RequestMapping("/assistiveaccessory")
public class AssistiveAccessoryController {
	@Autowired
    private AssistiveAccessoryRepository accessoryRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<AssistiveAccessory> accessoriesList = this.accessoryRepository.findAll();
        
        return new ModelAndView("assistiveaccessory/index","assistiveaccessories",accessoriesList);
    }
    
    @GetMapping("/new")
    public ModelAndView createForm(@ModelAttribute AssistiveAccessory assistiveaccessory) {
    	List<Category> categories = categoryRepository.findAll();
    	
    	HashMap<String, Object> dados = new HashMap<String, Object>();
    	dados.put("assistiveaccessory", assistiveaccessory);
    	dados.put("categories",categories);
    	
        return new ModelAndView("assistiveaccessory/form",dados);
    }
    
    @PostMapping(value="/new")
    public ModelAndView save(@Valid AssistiveAccessory assistiveaccessory, BindingResult result, RedirectAttributes redirect) {
        
    	assistiveaccessory = this.accessoryRepository.save(assistiveaccessory);
        
        return new ModelAndView("redirect:/assistiveaccessory");
    }
    
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") AssistiveAccessory assistiveaccessory) {
    	List<Category> categories = categoryRepository.findAll();
    	
    	HashMap<String, Object> dados = new HashMap<String, Object>();
    	dados.put("assistiveaccessory", assistiveaccessory);
    	dados.put("categories",categories);
    	
        return new ModelAndView("assistiveaccessory/form",dados);
    }
    
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.accessoryRepository.deleteById(id);
        return new ModelAndView("redirect:/assistiveaccessory");
    }
}
