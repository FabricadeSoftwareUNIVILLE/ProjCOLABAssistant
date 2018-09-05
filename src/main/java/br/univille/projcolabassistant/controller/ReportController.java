package br.univille.projcolabassistant.controller;

import static br.univille.projcolabassistant.constants.Constants.DEFAULT_NOT_FOUND_FILE;
import static br.univille.projcolabassistant.util.Util.toDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("")
	public String showReportOptionsPage() {
		return "report/choose-report-type";
	}

	@GetMapping("/user")
	public String usersReportCreationPage() {
		return "report/user-report";
	}

	@GetMapping("/order")
	public String ordersReportCreationPage() {
		return "report/order-report";
	}

	@GetMapping("/institution")
	public String createUserReport() {
		return "report/institution-report";
	}
	
	@GetMapping("/category")
	public String createCategoryReport() {
		return "report/category-report";
	}
	

	@GetMapping("/orderCategory")
  public String createOrderSumByCategoryReport() {
    return "report/orderCategory-report";
  }

	@RequestMapping(value="/download/category", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadCategoryReport(HttpServletResponse response) {
		try {
			File file = this.reportService.generateAcessoryCategoryReport();

			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_categoria.pdf");

			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);

			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client

			responseOutput.close();
			fileInput.close();

			if(!file.getName().equals(DEFAULT_NOT_FOUND_FILE)) {
				file.delete();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	@RequestMapping(value="/download/user", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadUserReport(String nameFilter, String emailFilter, String typeFilter, boolean orderByDesc, HttpServletResponse response) {
		try {
			File file = this.reportService.generateUserReport(nameFilter, emailFilter, typeFilter, orderByDesc);

			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_usuario.pdf");

			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);

			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client

			responseOutput.close();
			fileInput.close();

			if(!file.getName().equals(DEFAULT_NOT_FOUND_FILE)) {
				file.delete();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value="/download/order", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadOrderReport(
			@RequestParam("creationDateStart") String creationDateStart, 
			@RequestParam("creationDateEnd") String creationDateEnd, 
			@RequestParam("finishedDateStart") String finishedDateStart,
			@RequestParam("finishedDateEnd") String finishedDateEnd,
			@RequestParam("userName") String userName, 
			@RequestParam("status") Integer status, 
			@RequestParam(value="orderByDesc", defaultValue="false") Boolean isOrderByDesc, 
			HttpServletResponse response) {		
		try {		
			File file = this.reportService.generateOrderReport(toDate(creationDateStart), toDate(creationDateEnd), toDate(finishedDateStart), toDate(finishedDateEnd), userName, status, isOrderByDesc);

			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_pedidos.pdf");

			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);

			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client

			responseOutput.close();
			fileInput.close();

			if(!file.getName().equals(DEFAULT_NOT_FOUND_FILE)) {
				file.delete();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value="/download/institution", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadInstitutionReport(@RequestParam("nameFilter") String nameFilter, 
			@RequestParam("emailFilter") String emailFilter, 
			@RequestParam("cityFilter") String cityFilter,
			@RequestParam("isDesc") boolean isDesc,
			HttpServletResponse response) {		
		try {		
			File file = this.reportService.generateInstitutionReport(nameFilter, emailFilter, cityFilter, isDesc);

			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_instituicoes.pdf");

			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);

			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client

			responseOutput.close();
			fileInput.close();

			if(!file.getName().equals(DEFAULT_NOT_FOUND_FILE)) {
				file.delete();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="/download/orderCategory",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadOrderSumByCategoryReport(@RequestParam("categoryFilter") String categoryFilter, 
													 HttpServletResponse response) {
		System.out.println(categoryFilter);
		try {
			File file = this.reportService.generateOrderSumByCategoryReport(categoryFilter);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_num_pecas_categoria.pdf");
			
			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);
			
			IOUtils.copy(fileInput, responseOutput);
			
			responseOutput.close();
			fileInput.close();
			
			System.out.println("file.getName() = " + file.getName());
			
			if(!file.getName().equals(DEFAULT_NOT_FOUND_FILE)) {
				file.delete();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
