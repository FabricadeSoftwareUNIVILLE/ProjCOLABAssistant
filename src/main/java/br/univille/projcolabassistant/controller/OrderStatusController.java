package br.univille.projcolabassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderstatus")
public class OrderStatusController {
	@Autowired
	
	private OrderStatusController orderstatusRepository;

	
	@GetMapping("")
	public ModelAndView index() {
		return new ModelAndView("orderstatus/list");
	}
}
