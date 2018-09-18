package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/")
public class ConsultAccessoriesController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;

	@GetMapping("")
	public ModelAndView index(HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
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
	@GetMapping("/additem/{id}")
	public ModelAndView additem(@PathVariable("id") AssistiveAccessory assistiveAccessory,HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
		ItemShoppingCart itemShop = new ItemShoppingCart();
		itemShop.setAccessory(assistiveAccessory);
		shoppingCart.getItensList().add(itemShop);
		
		
		return new ModelAndView("redirect:/");
	}

}