package br.univille.projcolabassistant.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AccessoryColor;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.service.MyUserDetailsService;
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
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@GetMapping("")
	public ModelAndView index(HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
		List<AssistiveAccessoryViewModel> listAccessoryViewModel = this.consultAccessoriesRepository.findAllAssistiveAccessoryViewModel();
		
		HashMap<Category, List<AssistiveAccessoryViewModel>> data = new HashMap<Category, List<AssistiveAccessoryViewModel>>();
		
		Category lastCategory = null;
		List<AssistiveAccessoryViewModel> listAssistiveAccessory= null;
		for(AssistiveAccessoryViewModel item : listAccessoryViewModel) {
			if(lastCategory != item.getAssistiveAccessory().getCategory()) {
				lastCategory = item.getAssistiveAccessory().getCategory();
				listAssistiveAccessory= new  ArrayList<AssistiveAccessoryViewModel>();
				
				data.put(lastCategory, listAssistiveAccessory);
			}
			listAssistiveAccessory.add(item);
		}
				
		return new ModelAndView("catalog/accessoryList", "mapcategorylistAccessory", data);
	}
	@GetMapping("/additem/{idaccessory}/{idcolor}")
	public ModelAndView additem(@PathVariable("idaccessory") AssistiveAccessory assistiveAccessory,@PathVariable("idcolor") AccessoryColor accessoryColor,HttpSession session) {
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
		ItemShoppingCart itemShop = new ItemShoppingCart();
		itemShop.setAccessory(assistiveAccessory);
		itemShop.setColor(accessoryColor);
		shoppingCart.getItensList().add(itemShop);
		
		
		return new ModelAndView("redirect:/");
	}
	@GetMapping("/admin")
	public ModelAndView admin() {
		
		Collection<? extends GrantedAuthority> colRoles = userDetailsService.getUserRoles();
		
		for(GrantedAuthority item : colRoles) {
			if(item.getAuthority().equals("ROLE_USER"))
				return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("admin/admin");
	}
	@GetMapping("/about")
	public ModelAndView aboutPage() {
		return new ModelAndView("catalog/about");
	}

}