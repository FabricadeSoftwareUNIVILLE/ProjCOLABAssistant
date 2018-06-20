package br.univille.projcolabassistant.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.service.InstitutionReportService;

@Service
public class InstitutionReportServiceImpl implements InstitutionReportService {
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Override
	public File generateInstitutionReport() { return null; }
	
	@Override
	public String generatePDFInstitutionReport() {
		String resultReportPath = "result_report.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			List<Institution> institutions = this.institutionRepository.findAll();
			
			Gson gson = new Gson();
			
			System.out.println(gson.toJson(institutions));
			
			return gson.toJson(institutions);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
