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
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/MyList")
@PreAuthorize("hasRole('ROLE_USER')")
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

	@GetMapping("/conforder")
	public ModelAndView confOrder(HttpSession session) {

		ShoppingCart itemCart = new ShoppingCart();
		OrderRequest orderrequest = new OrderRequest();
		
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
			//orderitem.setAccessoryColor(item.getAccessory().get);
			//orderitem.setAccessorySize(size);

			orderrequest.getItensList().add(orderitem);
		}
		/*orderrequest.setUser(user);
		orderrequest.setOrderFinshDate(new Date());
		orderrequest.setOrderDate(new Date());
		orderrequest.setOrderFinshDate(new Date());
		orderrequest.setUserRequest(user);
		orderrequest.setInstitution(inst);
		orderRequestRepository.save(orderrequest);*/

		return new ModelAndView("catalog/shoppingCart");
	}
}