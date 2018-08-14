package br.univille.projcolabassistant.service.impl;

import static br.univille.projcolabassistant.constants.Constants.ANY_END_DATE;
import static br.univille.projcolabassistant.constants.Constants.ANY_START_DATE;
import static br.univille.projcolabassistant.constants.Constants.ANY_STATUS;
import static br.univille.projcolabassistant.constants.Constants.REPORTS_PATH;
import static br.univille.projcolabassistant.constants.Constants.RESULT_NOT_FOUND_FILE;
import static br.univille.projcolabassistant.util.Util.randomId;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.univille.projcolabassistant.model.AbstractReportObject;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.OrderRequestRepository;
import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private OrderRequestRepository orderRequestRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ITemplateEngine templateEngine;

	@Override
	public File generateUserReport(String nameFilter, String emailFilter, String typeFilter, boolean isOrderByDesc) {
		List<User> users;
		
		if(typeFilter.isEmpty()) {
			if(isOrderByDesc) {
				users = this.userRepository.searchWithFiltersDesc(nameFilter, emailFilter);
			} else {
				users = this.userRepository.searchWithFilters(nameFilter, emailFilter);
			}
		}
		else {
			if(isOrderByDesc) {
				users = this.userRepository.searchWithFiltersWithStatusDesc(nameFilter, emailFilter, typeFilter);
			} else {
				users = this.userRepository.searchWithFiltersWithStatus(nameFilter, emailFilter, typeFilter);
			}
		}
		
		return createPDFReport(users);
	}
	
	@Override
	public File generateInstitutionReport(String nameFilter, String emailFilter, String cityFilter) {
		List<Institution> institutions = this.institutionRepository.searchWithFilters(nameFilter, emailFilter, cityFilter);
		
		return createPDFReport(institutions);
	}
	
	@Override
	public File generateOrderReport(Date creationDateStart, Date creationDateEnd, Date finishedDateStart, Date finishedDateEnd, String userName, Integer status, Boolean isOrderByDesc) {
		creationDateStart = (creationDateStart == null) ? ANY_START_DATE : creationDateStart;
		creationDateEnd   = (creationDateEnd   == null) ? ANY_END_DATE : creationDateEnd;
		finishedDateStart = (finishedDateStart == null) ? ANY_START_DATE : finishedDateStart;
		finishedDateEnd   = (finishedDateEnd   == null) ? ANY_END_DATE : finishedDateEnd;
		
		List<OrderRequest> orders;
		
		if (isOrderByDesc) {
			if(status == ANY_STATUS) {
				orders = this.orderRequestRepository.searchBetweenDesc(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName);
			}
			else {
				orders = this.orderRequestRepository.searchBetweenWithStatusDesc(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName, status);
			}
		} else {
			if(status == ANY_STATUS) {
				orders = this.orderRequestRepository.searchBetweenAsc(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName);
			}
			else {
				orders = this.orderRequestRepository.searchBetweenWithStatusAsc(creationDateStart, creationDateEnd, finishedDateStart, finishedDateEnd, userName, status);
			}
		}
	
		return createPDFReport(orders);
	}
	
	@Override
	public File generateAcessoryCategoryReport() {
		List<Category> listCategory = this.categoryRepository.findAll();
	
		return createPDFReport(listCategory);
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
			context.setLocale(Locale.ENGLISH);
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
	
	public void setTemplateEngine(ITemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
}
