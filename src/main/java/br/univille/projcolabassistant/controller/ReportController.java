package br.univille.projcolabassistant.controller;

import static br.univille.projcolabassistant.util.Util.toDate;
import static br.univille.projcolabassistant.constants.Constants.DEFAULT_NOT_FOUND_FILE;

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

import br.univille.projcolabassistant.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	
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
	
	@GetMapping("/accessory")
    public String createAccessoryReport() {
        return "report/accessory-report";
    }
	
	@RequestMapping(value="/download/user", 
            		method=RequestMethod.GET,
            		produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadUserReport(String nameFilter, String emailFilter, String typeFilter, HttpServletResponse response) {
		try {
			File file = this.reportService.generateUserReport(nameFilter, emailFilter, typeFilter);

			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_usuarios.pdf");
			
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
			                                   HttpServletResponse response) {		
		try {		
			File file = this.reportService.generateOrderReport(toDate(creationDateStart), toDate(creationDateEnd), toDate(finishedDateStart), toDate(finishedDateEnd), userName, status);
			
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
													 HttpServletResponse response) {		
		try {		
			File file = this.reportService.generateInstitutionReport(nameFilter, emailFilter, cityFilter);
			
			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_instituicoes.pdf");
			
			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);
			
			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client
			
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
	
	@RequestMapping(value="/download/accessories", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadAccessoryReport(@RequestParam("categoryFilter") String categoryFilter, 
													 HttpServletResponse response) {		
	try {		
		File file = this.reportService.generateAccessoryReport(categoryFilter);
		
		response.setContentType("application/pdf");   
		response.setHeader("Content-Disposition", "attachment; filename = relNumPecasProdTipoAcess.pdf");
		
		OutputStream responseOutput = response.getOutputStream();
		FileInputStream fileInput = new FileInputStream(file);
		
		IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client
		
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
