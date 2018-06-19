package br.univille.projcolabassistant.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/download/user", method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource downloadFile() {
		File file = this.userReportService.generateUserReport();
		
	    return new FileSystemResource(file);
	}

}
