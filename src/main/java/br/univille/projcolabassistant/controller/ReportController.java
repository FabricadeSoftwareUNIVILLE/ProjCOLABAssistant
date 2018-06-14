package br.univille.projcolabassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		this.userReportService.generateUserReport();
		
        return "report/user-report";
    }

}
