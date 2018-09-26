package br.univille.projcolabassistant.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.model.OrderStatusLog;
import br.univille.projcolabassistant.repository.OrderRequestRepository;

@Controller
@RequestMapping("/orderstatus")

public class OrderStatusController {
	@Autowired
	private OrderRequestRepository orderRequestRepository;

	@GetMapping("")
	public ModelAndView index() {
		
		//Adaptamos os nomes das classes e das listas para o nosso padrão
		List<OrderStatusLog> Orders = orderRequestRepository.findByorderFinshDateNullOrderByOrderDateAsc();
		List<OrderStatusLog> finishedOrders = orderRequestRepository.findByorderFinshDateNotNullOrderByOrderDateAsc();
		HashMap<String, Object> orderdata = new HashMap<String, Object>();
		orderdata.put("orderstatus", Orders);
		orderdata.put("finishedOrders", finishedOrders);
		
		
		return new ModelAndView("reportorderstatus/index",orderdata);
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") OrderRequest orderRequest) {

		return new ModelAndView("orderstatus/detail","orderRequest",orderRequest);
	}

	@GetMapping("/change/{id}")
	
	public ModelAndView changeStatus(@PathVariable("id") OrderRequest orderRequest) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("orderRequest", orderRequest);
		data.put("newOrderStatusLog", new OrderStatusLog());
		return new ModelAndView("orderstatus/change",data);
	}
	
	@PostMapping(params="form",value="/{id}")
	public ModelAndView save(@PathVariable("id") OrderRequest orderRequest, @Valid OrderStatusLog newOrderStatusLog, BindingResult result, RedirectAttributes redirect) {
		
		//Retiramos a autenticação de usuário, conforme a orientação do professor
		//mudamos o nome do método para o nosso padrão
		newOrderStatusLog.setDate(new Date());
		if(newOrderStatusLog.getStatus() == 50) {
			orderRequest.setOrderFinshDate(new Date());
		}
		orderRequest.getOrderStatusLogList().add(newOrderStatusLog);
		orderRequest.setStatus(newOrderStatusLog.getStatus());
		orderRequestRepository.save(orderRequest);
		
		
		return new ModelAndView("redirect:/orderstatus");
	}
}
