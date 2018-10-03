package br.univille.projcolabassistant.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.viewmodel.OrderRequestStatusAPI;


@RestController
@RequestMapping("/api")
public class ColabAssistantAPI {
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@GetMapping("/orderrequeststatus")
	public ResponseEntity<OrderRequestStatusAPI> listarPacientes() {
		OrderRequestStatusAPI respStatus = new OrderRequestStatusAPI();
		respStatus.setNumberOpenOrderRequest(orderRequestRepository.countByorderFinshDateNull());
		respStatus.setNumberNewOrderRequest(orderRequestRepository.countByorderFinshDateNullAndUserNull());
		
		return new ResponseEntity<OrderRequestStatusAPI>(respStatus,HttpStatus.OK);
	}
	
}
