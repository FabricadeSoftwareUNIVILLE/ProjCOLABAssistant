package br.univille.projcolabassistant.service;

import java.io.File;
import java.util.Date;

public interface OrderReportService {
	
	File generateOrderReport(Date creationDateStart, Date creationDateEnd, Date finishedDateStart, Date finishedDateEnd, int status);
	
}
