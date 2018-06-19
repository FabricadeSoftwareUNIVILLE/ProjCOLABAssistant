package br.univille.projcolabassistant.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.service.InstitutionReportService;

@Service
public class InstitutionReportServiceImpl implements InstitutionReportService {
	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Override
	public File generateInstitutionReport() {
		String resultReportPath = "result_report.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			List<Institution> institutions = this.institutionRepository.findAll();
			
			writer.write("Id;Nome;Descrição;Endereço;Celular;E-mail;Cidade\n");
			
			for(Institution institution : institutions) {
				writer.write(institution.getId() + ";" + institution.getName() + ";" + institution.getDescription() + ";" + institution.getAddress() + ";" + institution.getPhone() + ";" + institution.getEmail() + ";" + institution.getCity());
				writer.write("\n");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
		
		return new File(resultReportPath);
	}
}
