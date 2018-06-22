package br.univille.projcolabassistant.service.impl;

import static br.univille.projcolabassistant.util.Util.randomId;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.univille.projcolabassistant.model.AbstractReportObject;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	private final String REPORTS_PATH = "src/main/resources/temporary-persistent-reports/";
	private final String RESULT_NOT_FOUND_FILE = "src/main/resources/templates/report/not-found-result.pdf";
	private final Date ANY_END_DATE = new Date(253402221600000L);   //<= 9999-12-31
	private final Date ANY_START_DATE = new Date(-30610212812000L); //>= 1000-01-01
	private final Integer ANY_STATUS = null;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@Autowired
	private ITemplateEngine templateEngine;
	
	@Override
	public File generateUserReport(String nameFilter, String emailFilter, String typeFilter) {
		List<User> users;
		
		if(typeFilter.isEmpty()) {
			users = this.userRepository.searchWithFilters(nameFilter, emailFilter);
		}
		else {
			users = this.userRepository.searchWithFiltersWithStatus(nameFilter, emailFilter, typeFilter);
		}
		
		return createPDFReport(users);
	}
	
	@Override
	public File generateInstitutionReport(String nameFilter, String emailFilter, String cityFilter) {
		List<Institution> institutions = this.institutionRepository.searchWithFilters(nameFilter, emailFilter, cityFilter);
		
		return createPDFReport(institutions);
	}
	
	@Override
	public File generateOrderReport(Date creationDateStart, Date creationDateEnd, Date finishedDateStart, Date finishedDateEnd, String userName, Integer status) {
		creationDateStart = (creationDateStart == null) ? ANY_START_DATE : creationDateStart;
		creationDateEnd   = (creationDateEnd   == null) ? ANY_END_DATE : creationDateEnd;
		finishedDateStart = (finishedDateStart == null) ? ANY_START_DATE : finishedDateStart;
		finishedDateEnd   = (finishedDateEnd   == null) ? ANY_END_DATE : finishedDateEnd;
		
		List<OrderRequest> orders;
		
		if(status == ANY_STATUS) {
			orders = this.orderRequestRepository.searchBetween(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName);
		}
		else {
			orders = this.orderRequestRepository.searchBetweenWithStatus(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName, status);
		}
	
		return createPDFReport(orders);
	}

	@SuppressWarnings("unchecked")
	private File createPDFReport(Object reportObjects) {
		try {
			List<AbstractReportObject> objects = (List<AbstractReportObject>) reportObjects;
			
			if(objects.size() == 0) {
				return new File(RESULT_NOT_FOUND_FILE);
			}
			
			String reportType = objects.get(0).getReportType();
			String template = objects.get(0).getTemplatePath();
			
			Context context = new Context();
			context.setVariable(reportType, reportObjects);
			
			String processedHtml = templateEngine.process(template, context);
			
			File outputFile = new File(REPORTS_PATH + reportType + randomId() + ".pdf");
			FileOutputStream outputStream = new FileOutputStream(outputFile);
	
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(outputStream, false);
			renderer.finishPDF();
			
			outputStream.close();
			
			return outputFile;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
}
