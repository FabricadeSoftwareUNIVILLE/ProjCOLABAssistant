package br.univille.projcolabassistant.controller;

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

import br.univille.projcolabassistant.service.OrderReportService;
import br.univille.projcolabassistant.service.UserReportService;
import static br.univille.projcolabassistant.util.Util.toDate;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private UserReportService userReportService;
	
	@Autowired
	private OrderReportService orderReportService;
	
	@GetMapping("")
    public String showReportOptionsPage() {
        return "report/index";
    }
	
	@GetMapping("/user")
    public String usersReportCreationPage() {
        return "report/user-report";
    }
	
	@GetMapping("/order")
    public String ordersReportCreationPage() {
        return "report/order-report";
    }
	
	@RequestMapping(value="/download/user", 
            		method=RequestMethod.GET,
            		produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadUserReport(String nameFilter, String emailFilter, String typeFilter, HttpServletResponse response) {
		try {
			File file = this.userReportService.generateUserReport(emailFilter, nameFilter, typeFilter);

			response.setContentType("application/csv");   
			response.setHeader("Content-Disposition", "filename = relatorio_usuario.csv");
			
			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);
			
			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client
			
			responseOutput.close();
			fileInput.close();
			
			file.delete();
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
											   @RequestParam("status") Integer status, 
			                                   HttpServletResponse response) {		
		try {		
			File file = this.orderReportService.generateOrderReport(toDate(creationDateStart), toDate(creationDateEnd), toDate(finishedDateStart), toDate(finishedDateEnd), status);
			
			response.setContentType("application/csv");   
			response.setHeader("Content-Disposition", "filename = relatorio_pedidos.csv");
			
			OutputStream responseOutput = response.getOutputStream();
			FileInputStream fileInput = new FileInputStream(file);
			
			IOUtils.copy(fileInput, responseOutput); //enviando o response, contendo o arquivo, para o client
			
			responseOutput.close();
			fileInput.close();
			
			file.delete();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
