package br.univille.projcolabassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.valueobject.ItemShoppingCart;
import br.univille.projcolabassistant.valueobject.ShoppingCart;

@Controller
@RequestMapping("/order")

public class OrderController {
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
			orderrequest.getItensList().add(orderitem);
		}
		
		orderRequestRepository.save(orderrequest);
	}
	
	@GetMapping("/list")
    public ModelAndView list(@ModelAttribute OrderRequest order) {
        List<OrderRequest> listOrder = orderRequestRepository.findAll();
        
        return new ModelAndView("order/list", "listorder", listOrder);
    }
}