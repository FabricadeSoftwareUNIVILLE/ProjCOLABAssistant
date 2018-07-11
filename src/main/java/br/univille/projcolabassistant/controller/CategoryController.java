package br.univille.projcolabassistant.controller;



import static br.univille.projcolabassistant.constants.Constants.DEFAULT_NOT_FOUND_FILE;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.service.ReportService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	

}
