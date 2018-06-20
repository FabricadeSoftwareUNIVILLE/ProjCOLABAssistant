package br.univille.projcolabassistant.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.service.OrderReportService;

@Service
public class OrderReportServiceImpl implements OrderReportService {
	private final String REPORTS_PATH = "src/main/resources/temporary-persistent-reports/";
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@Override
	public File generateOrderReport(Date creationDateStart, Date creationDateEnd, Date finishedDateStart, Date finishedDateEnd, int status) {
		String resultReportPath = REPORTS_PATH + "order_" + randomId() + ".csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			//TODO chamar métodos de repositório diferente dependendo do filtro (pela criação, pelo finished, pelo status, etc.)
			List<OrderRequest> orders = this.orderRequestRepository.findAll();
			
			writer.write("id;orderDate;orderFinishDate;status\n");
			
			for(OrderRequest order : orders) {
				writer.write(String.format("%s;%s;%s;%s\n", order.getId(), order.getOrderDate(), order.getOrderFinshDate(), order.getStatus()));
			}
						
			return new File(resultReportPath);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
	
	private String randomId() {
		return UUID.randomUUID().toString();
	}
	
}
