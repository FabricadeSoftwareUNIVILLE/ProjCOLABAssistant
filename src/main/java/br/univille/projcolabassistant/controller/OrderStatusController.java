package br.univille.projcolabassistant.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import br.univille.projcolabassistant.service.MyUserDetailsService;

@Controller
@RequestMapping("/orderstatus")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class OrderStatusController {
	@Autowired
	private OrderRequestRepository orderRequestRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@GetMapping("")
	public ModelAndView index() {
		
		List<OrderRequest> listOrder = orderRequestRepository.findByorderFinshDateNullOrderByOrderDateAsc();
		List<OrderRequest> listOrderDone = orderRequestRepository.findByorderFinshDateNotNullOrderByOrderDateAsc();
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listOrder", listOrder);
		dados.put("listOrderDone", listOrderDone);
		
		
		return new ModelAndView("orderstatus/list",dados);
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") OrderRequest orderRequest) {

		return new ModelAndView("orderstatus/detail","orderRequest",orderRequest);
	}

	@GetMapping("/change/{id}")
	public ModelAndView changeStatus(@PathVariable("id") OrderRequest orderRequest) {
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("orderRequest", orderRequest);
		dados.put("newOrderStatusLog", new OrderStatusLog());
		return new ModelAndView("orderstatus/change",dados);
	}
	@PostMapping(params="form",value="/{id}")
	public ModelAndView save(@PathVariable("id") OrderRequest orderRequest, @Valid OrderStatusLog newOrderStatusLog, BindingResult result, RedirectAttributes redirect) {

		orderRequest.setUser(userDetailsService.getUserLogged());
		newOrderStatusLog.setDate(new Date());
		if(newOrderStatusLog.getStatusLog() >= 50) {
			orderRequest.setOrderFinshDate(new Date());
		}else {
			orderRequest.setOrderFinshDate(null);
		}
		newOrderStatusLog.setUser(userDetailsService.getUserLogged());
		orderRequest.getOrderStatusLogList().add(newOrderStatusLog);
		orderRequest.setStatus(newOrderStatusLog.getStatusLog());
		orderRequestRepository.save(orderRequest);
		
		
		return new ModelAndView("redirect:/orderstatus");
	}
}
