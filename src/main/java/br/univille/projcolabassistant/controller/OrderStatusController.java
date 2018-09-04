package br.univille.projcolabassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;

@Controller
@RequestMapping("/orderstatus")
public class OrderStatusController {
	@Autowired
	
	private OrderStatusController orderstatusRepository;

	
	@GetMapping("")
	public ModelAndView index() {
		OrderRequest order = new OrderRequest();
		
		AssistiveAccessory accessoryA = new AssistiveAccessory();
		accessoryA.setCode("111");
		accessoryA.setDescription("this is a description");
		accessoryA.setFunction("this is a function");
		accessoryA.setId(111);
		accessoryA.setName("accessoryA");
		accessoryA.setPrescription("this is prescription");
		
		OrderItems orderitem = new OrderItems();
		orderitem.setQuantity(3);
		orderitem.setAccessory(accessoryA);
		//orderitem.setAccessoryColor(item.getAccessoryColor());
		//orderitem.setAccessorySize(item.getAccessorySize());
		order.getItensList().add(orderitem);
		
		return new ModelAndView("orderstatus/list");
	}
}
