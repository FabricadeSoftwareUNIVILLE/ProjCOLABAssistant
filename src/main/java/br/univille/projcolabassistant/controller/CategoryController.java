package br.univille.projcolabassistant.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		return new ModelAndView("category/index");
	}

}
