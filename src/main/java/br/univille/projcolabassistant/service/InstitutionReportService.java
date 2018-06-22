package br.univille.projcolabassistant.service;

import java.io.File;

public interface InstitutionReportService {
	File generateInstitutionReport(String nameFilter, String emailFilter, String cityFilter);
}
