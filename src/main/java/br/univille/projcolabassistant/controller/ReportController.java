package br.univille.projcolabassistant.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.univille.projcolabassistant.service.InstitutionReportService;
import br.univille.projcolabassistant.util.PdfGenaratorUtil;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private InstitutionReportService institutionReportService;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@GetMapping("")
    public String showReportOptionsPage() {
        return "report/index";
    }
	
	@GetMapping("/institution")
    public String createUserReport() {
        return "report/institution-report";
    }
	
//	@RequestMapping(value="/download/institution", method=RequestMethod.GET)
//	@ResponseBody
//	public FileSystemResource downloadFile() {
//		File file = this.institutionReportService.generateInstitutionReport();
//		
//	    return new FileSystemResource(file);
//	}
	
	@RequestMapping(value="/download/pdf/institution", method=RequestMethod.GET/*, produces=MediaType.APPLICATION_PDF_VALUE*/)
	@ResponseBody
	public FileSystemResource downloadPDF() throws Exception {
		Map<String,String> data = new HashMap<String,String>();
	    data.put("name","James");
	    File file = pdfGenaratorUtil.createPdf("pdf-template", data);
	    
	    this.institutionReportService.generatePDFInstitutionReport();
	    
		return new FileSystemResource(file);
	}

}
