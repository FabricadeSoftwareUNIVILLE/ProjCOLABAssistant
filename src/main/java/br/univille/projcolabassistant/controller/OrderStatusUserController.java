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
@RequestMapping("/orderstatususer")
@PreAuthorize("hasRole('ROLE_USER')")
public class OrderStatusUserController {
	@Autowired
	private OrderRequestRepository orderRequestRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@GetMapping("")
	public ModelAndView index() {
		
		List<OrderRequest> listOrder = orderRequestRepository.findByUserRequestAndOrderFinshDateNullOrderByOrderDateAsc(userDetailsService.getUserLogged());
		List<OrderRequest> listOrderDone = orderRequestRepository.findByUserRequestAndOrderFinshDateNotNullOrderByOrderDateAsc(userDetailsService.getUserLogged());
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("listOrder", listOrder);
		dados.put("listOrderDone", listOrderDone);
		
		
		return new ModelAndView("orderstatususer/list",dados);
	}

	@GetMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") OrderRequest orderRequest) {

		return new ModelAndView("orderstatususer/detail","orderRequest",orderRequest);
	}
}
