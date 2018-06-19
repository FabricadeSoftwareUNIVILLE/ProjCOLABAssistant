package br.univille.projcolabassistant.service;

import java.io.File;

public interface UserReportService {
	
	File generateUserReport(String nameFilter, String emailFilter, String typeFilter);
	
}
