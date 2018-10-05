package br.univille.projcolabassistant.constants;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
	@Value("${accessoryimages.path}")
	public static String REPORTS_PATH;
	//public static final String RESULT_NOT_FOUND_FILE = "/src/main/resources/templates/report/not-found-result.pdf";
	public static final String RESULT_NOT_FOUND_FILE = "/static/pdf/not-found-result.pdf";
	public static final String DEFAULT_NOT_FOUND_FILE = "not-found-result.pdf";
	public static final String EXPECTED_FILES = "src/test/java/expected-files-test/";
	public static final Date ANY_END_DATE = new Date(253402221600000L);   //<= 9999-12-31
	public static final Date ANY_START_DATE = new Date(-30609780812000L); //>= 1000-01-01
	public static final Integer ANY_STATUS = null;
}
