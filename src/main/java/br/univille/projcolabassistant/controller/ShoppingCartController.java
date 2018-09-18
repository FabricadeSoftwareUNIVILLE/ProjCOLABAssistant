package br.univille.projcolabassistant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/MyList")
public class ShoppingCartController {

	@Autowired
	private ShoppingCart shoppingCart;
	
	@GetMapping("")
	public ModelAndView index(HttpSession session) {
				
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
				
		return new ModelAndView("catalog/shoppingCart", "shoppingcart", shoppingCart);
	}

}