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

import br.univille.projcolabassistant.service.UserReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private UserReportService userReportService;
	
	@GetMapping("")
    public String showReportOptionsPage() {
        return "report/index";
    }
	
	@GetMapping("/user")
    public String createUserReport() {
        return "report/user-report";
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

}
