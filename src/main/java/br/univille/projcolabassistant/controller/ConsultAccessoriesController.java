package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;

@Controller
@RequestMapping("/")
public class ConsultAccessoriesController {

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessoryViewModel> listAccessoryViewModel = this.consultAccessoriesRepository.findAllAssistiveAccessoryViewModel();
		
		HashMap<Category, List<AssistiveAccessory>> data = new HashMap<Category, List<AssistiveAccessory>>();
		
		
		Category lastCategory = null;
		List<AssistiveAccessory> listAssistiveAccessory= null;
		for(AssistiveAccessoryViewModel item : listAccessoryViewModel) {
			if(lastCategory != item.getAssistiveAccessory().getCategory()) {
				lastCategory = item.getAssistiveAccessory().getCategory();
				listAssistiveAccessory= new  ArrayList<AssistiveAccessory>();
				
				data.put(lastCategory, listAssistiveAccessory);
			}
			listAssistiveAccessory.add(item.getAssistiveAccessory());
		}
				
		return new ModelAndView("catalog/accessoryList", "mapcategorylistAccessory", data);
	}

}