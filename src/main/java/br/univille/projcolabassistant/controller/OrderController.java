package br.univille.projcolabassistant.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/orderrequest")

public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@GetMapping("")
	public void create() {
		
		ShoppingCart shoppingcart = new ShoppingCart();
		OrderRequest orderrequest = new OrderRequest();
		
		ItemShoppingCart item1 = new ItemShoppingCart();
		ItemShoppingCart item2 = new ItemShoppingCart();
		ItemShoppingCart item3 = new ItemShoppingCart();
		
		shoppingcart.getItensList().add(item1);
		shoppingcart.getItensList().add(item2);
		shoppingcart.getItensList().add(item3);
		
		for(ItemShoppingCart item : shoppingcart.getItensList()) {
			
			OrderItems orderitem = new OrderItems();
			orderitem.setQuantity(item.getQuantity());
			orderitem.setAccessory(item.getAccessory());
			//orderitem.setAccessoryColor(item.getAccessoryColor);
			//orderitem.setAccessorySize(item.getAccessorySize);
			orderrequest.getItensList().add(orderitem);
		}
		
	}
	
	
}
