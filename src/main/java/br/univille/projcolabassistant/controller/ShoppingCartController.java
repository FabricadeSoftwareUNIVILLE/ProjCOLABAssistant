package br.univille.projcolabassistant.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.model.OrderItems;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.model.OrderStatusLog;
import br.univille.projcolabassistant.repository.AccessorySizeRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.service.MyUserDetailsService;
import br.univille.projcolabassistant.viewmodel.ItemShoppingCart;
import br.univille.projcolabassistant.viewmodel.ShoppingCart;

@Controller
@RequestMapping("/MyList")
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController {

	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private AccessorySizeRepository accessorySizeRepository;
	@Autowired
	private InstitutionRepository institutionRepository;
	
	
	@GetMapping("")
	public ModelAndView index(HttpSession session) {

		
		ShoppingCart shoppingCart = null;
		shoppingCart = (ShoppingCart) session.getAttribute("carrinho");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("carrinho", shoppingCart);
		}
		
		
		HashMap<String, Object> dados = new HashMap<String, Object>();
		dados.put("shoppingcart", shoppingCart);
		dados.put("listInstitution", userDetailsService.getUserLogged().getInstitutionList());
		
		
		
		return new ModelAndView("catalog/shoppingCart", dados);
	}
	
	
	@PostMapping(params="form")
    public ModelAndView save(@Valid ShoppingCart shoppingcart, BindingResult result, RedirectAttributes redirect) {
       
		ShoppingCart itemCart = new ShoppingCart();
		OrderRequest orderrequest = new OrderRequest();
		
		//Método que retorna a instância da classe User logada no sistema
		orderrequest.setUserRequest(userDetailsService.getUserLogged());
		
		for (ItemShoppingCart item : shoppingCart.getItensList()) {
			OrderItems orderitem = new OrderItems();
			orderitem.setQuantity(item.getQuantity());
			orderitem.setAccessory(item.getAccessory());
			orderitem.setAccessoryColor(item.getColor());
			orderitem.setAccessorySize(item.getSize());

			orderrequest.getItensList().add(orderitem);
		}
		
		OrderStatusLog status = new OrderStatusLog();
		status.setUser(null);
		status.setDate(new Date());
		status.setStatusLog(0);
		
		orderrequest.setOrderDate(new Date());
		orderrequest.setOrderFinshDate(null);
		orderrequest.setStatus(0);
		orderrequest.getOrderStatusLogList().add(status);
		orderrequest.setInstitution(shoppingcart.getInstitution());
		orderrequest.setUser(null);
		orderrequest.setUserRequest(userDetailsService.getUserLogged());
		orderRequestRepository.save(orderrequest);

		shoppingCart.getItensList().clear();
		
		return new ModelAndView("redirect:/");
    }

}