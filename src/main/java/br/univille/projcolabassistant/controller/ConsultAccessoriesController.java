package br.univille.projcolabassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;

@Controller
@RequestMapping("/catalogo")
public class ConsultAccessoriesController {

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessory> listAccessory = this.consultAccessoriesRepository.findAll();
				
		return new ModelAndView("/catalog/accessoryList", "listAccessory", listAccessory);
	}

}