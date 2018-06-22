package br.univille.projcolabassistant.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.service.InstitutionReportService;
import br.univille.projcolabassistant.util.PdfGenaratorUtil;

@Service
public class InstitutionReportServiceImpl implements InstitutionReportService {
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private PdfGenaratorUtil pdfGenaratorUtil;

	@Override
	public File generateInstitutionReport(String nameFilter, String emailFilter, String cityFilter) {
		List<Institution> institutions = this.institutionRepository.searchWithFilters(nameFilter, emailFilter, cityFilter);

	    File file = pdfGenaratorUtil.createPdf("report/institution-pdf-template", institutions);
		
		return file;
	}
}
