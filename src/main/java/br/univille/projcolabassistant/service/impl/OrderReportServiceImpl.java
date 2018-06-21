package br.univille.projcolabassistant.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.service.OrderReportService;
import static br.univille.projcolabassistant.util.Util.randomId;

@Service
public class OrderReportServiceImpl implements OrderReportService {
	private final String REPORTS_PATH = "src/main/resources/temporary-persistent-reports/";
	private final Date ANY_END_DATE = new Date(253402221600000L);   //<= 9999-12-31
	private final Date ANY_START_DATE = new Date(-30610212812000L); //>= 1000-01-01
	private final Integer ANY_STATUS = null;
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@Override
	public File generateOrderReport(Date creationDateStart, Date creationDateEnd, Date finishedDateStart, Date finishedDateEnd, Integer status) {
		String resultReportPath = REPORTS_PATH + "order_" + randomId() + ".csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			creationDateStart = (creationDateStart == null) ? ANY_START_DATE : creationDateStart;
			creationDateEnd   = (creationDateEnd   == null) ? ANY_END_DATE : creationDateEnd;
			finishedDateStart = (finishedDateStart == null) ? ANY_START_DATE : finishedDateStart;
			finishedDateEnd   = (finishedDateEnd   == null) ? ANY_END_DATE : finishedDateEnd;
			
			List<OrderRequest> orders;
			
			if(status == ANY_STATUS) {
				orders = this.orderRequestRepository.searchBetween(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd);
			}
			else {
				orders = this.orderRequestRepository.searchBetweenWithStatus(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, status);
			}
			
			writer.write("id;orderDate;orderFinishDate;status;user;userRequest\n");
			
			for(OrderRequest order : orders) {
				writer.write(String.format("%s;%s;%s;%s;%s;%s\n", order.getId(), order.getOrderDate(), order.getOrderFinshDate(), order.getStatus(), order.getUser().getEmail(), order.getUserRequest().getEmail()));
			}
						
			return new File(resultReportPath);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
	
}
