package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
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

import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.CategoryRepository;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
    private CategoryRepository categoryRepository;
	
    @GetMapping("")
    public ModelAndView index() {
    	List<Category> listaCategory = this.categoryRepository.findAll();
    	/*
    	List<Category> listaCategory = new ArrayList<Category>();
		Category p1 = new Category();
        p1.setId(1);
        p1.setName("Minha primeira categoria");
        listaCategory.add(p1);
        */
        
        return new ModelAndView("category/index","listacat",listaCategory);
    }
    @GetMapping("/novo")
    public String createForm(@ModelAttribute Category category) {
        return "category/form";
    }
    @PostMapping(params="form")
    public ModelAndView save(@Valid Category category, BindingResult result, RedirectAttributes redirect) {
        
        category = this.categoryRepository.save(category);
        
        return new ModelAndView("redirect:/category");
    }
    @GetMapping(value="/update/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Category category) {
        return new ModelAndView("category/form","category",category);
    }
    @GetMapping(value="delete/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.categoryRepository.deleteById(id);
        return new ModelAndView("redirect:/category");
    }
}
