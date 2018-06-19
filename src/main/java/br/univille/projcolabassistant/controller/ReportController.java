package br.univille.projcolabassistant.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/download/institution", method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadFile() {
		File file = this.institutionReportService.generateInstitutionReport();
		
	    return new FileSystemResource(file);
	}

}
