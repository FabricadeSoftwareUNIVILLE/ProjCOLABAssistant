package br.univille.projcolabassistant.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import br.univille.projcolabassistant.model.Institution;

@Component
public class PdfGenaratorUtil {
	@Autowired
	private ITemplateEngine templateEngine;
	
	private final String REPORTS_PATH = "src/main/resources/temporary-persistent-reports/";

	public File createPdf(String templateName, List<Institution> institutions) {
		try {
			Context context = new Context();
			context.setVariable("institutions", institutions);
			
			String processedHtml = templateEngine.process(templateName, context);
			
			File outputFile = new File(REPORTS_PATH + "relatorio_instituicao_");
			FileOutputStream outputStream = new FileOutputStream(outputFile);
	
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(outputStream, false);
			renderer.finishPDF();
			
			outputStream.close();
			
			return outputFile;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
}