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

import br.univille.projcolabassistant.service.InstitutionReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private InstitutionReportService institutionReportService;
	
	@GetMapping("")
    public String showReportOptionsPage() {
        return "report/index";
    }
	
	@GetMapping("/institution")
    public String createUserReport() {
        return "report/institution-report";
    }
	
	@RequestMapping(value="/download/institution", 
    				method=RequestMethod.GET,
    				produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void generateAndDownloadInstitutionReport(@RequestParam("nameFilter") String nameFilter, 
													 @RequestParam("emailFilter") String emailFilter, 
													 @RequestParam("cityFilter") String cityFilter,
													 HttpServletResponse response) {		
		try {		
			File file = this.institutionReportService.generateInstitutionReport(nameFilter, emailFilter, cityFilter);
			
			response.setContentType("application/pdf");   
			response.setHeader("Content-Disposition", "attachment; filename = relatorio_instituicoes.pdf");
			
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
