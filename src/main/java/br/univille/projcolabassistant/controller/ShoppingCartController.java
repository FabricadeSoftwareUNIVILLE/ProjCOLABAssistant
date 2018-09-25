package br.univille.projcolabassistant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.service.MyUserDetailsService;
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/MyList")
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController {

	@Autowired
	private ShoppingCart shoppingCart;
	
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
		return new ModelAndView("catalog/shoppingCart", "shoppingcart", shoppingCart);
	}

	@GetMapping("/conforder")
	public ModelAndView confOrder(HttpSession session) {

		ShoppingCart itemCart = new ShoppingCart();
		OrderRequest orderrequest = new OrderRequest();
		
		//Método que retorna a instância da classe User logada no sistema
		orderrequest.setUserRequest(userDetailsService.getUserLogged());
		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		

		for (ItemShoppingCart item : shoppingCart.getItensList()) {
						
			OrderItems orderitem = new OrderItems();
			orderitem.setQuantity(item.getQuantity());
			orderitem.setAccessory(item.getAccessory());
			orderitem.setAccessoryColor(item.getColor());
			//orderitem.setAccessorySize(item.getAccessory());

			orderrequest.getItensList().add(orderitem);
		}
		orderrequest.setUser(userDetailsService.getUserLogged());
		orderrequest.setOrderFinshDate(null);
		//orderrequest.setOrderDate(new Date());
		orderrequest.setUserRequest(userDetailsService.getUserLogged());
		//orderrequest.setInstitution();
		//orderRequestRepository.save(orderrequest);

		shoppingCart.getItensList().clear();
		
		return new ModelAndView("catalog/shoppingCart");
	}
}