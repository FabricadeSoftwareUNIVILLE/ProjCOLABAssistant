package br.univille.projcolabassistant;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.univille.projcolabassistant.controller.AccessoryColorController;
import br.univille.projcolabassistant.controller.CategoryController;
import br.univille.projcolabassistant.controller.InstitutionController;
import br.univille.projcolabassistant.controller.UserController;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjColabAssistantApplicationTests {
	
	@Autowired
	private CategoryController categoryController;
	
	@Autowired
	private AccessoryColorController accessorycontroller;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InstitutionController InstitutionController;
	
	@Autowired
	private CityRepository cityRepository; 

	@Autowired
	private UserController controller;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AccessoryColorRepository accessorycolorRepository;
	
	
	@Test
	public void contextLoads() {
		//Verifica a existência da instância do controlador

		assertThat(InstitutionController).isNotNull();
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void categoryController() throws Exception {
	
		categoryRepository.deleteAll();
		categoryRepository.flush();
		
		//when(categoryRepository.findAll()).thenReturn(asList(category));
		
		
		this.mockMvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&name=roberta"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/category"));
		
	    this.mockMvc.perform(get("/category")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("roberta"));	      

	}
	
	@Test
	public void AccessoryColorController() throws Exception {
	
		accessorycolorRepository.deleteAll();
		accessorycolorRepository.flush();
		
		//when(categoryRepository.findAll()).thenReturn(asList(category));
		
		
		this.mockMvc.perform(post("/accessorycolor")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&name=verdinho"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/accessorycolor"));
		
	    this.mockMvc.perform(get("/accessorycolor")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("verdinho"));	      
	}
	
	
	@Test
	public void pacienteControllerTest() throws Exception {
		//Teste do método index
		this.mockMvc.perform(get("/consultecategory")).andDo(print()).andExpect(status().isOk())
		.andExpect(xpath("//table").exists())
		.andExpect(xpath("//td[contains(., 'Zezinho')]").exists());
		}


	@Test
	public void institutionControllerTest() throws Exception {
		//Teste do método index
		this.mockMvc.perform(get("/Institution")).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table").exists());
	}


	@Test
	public void institutionControllerSaveTest() throws Exception {
		
		
		City c = new City();	
		c.setName("Joinville");
		c.setState("SC");
		
		cityRepository.save(c);
		cityRepository.flush();
		
		this.mockMvc.perform(post("/Institution")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&address=rua&description=descricao&email=teste@teste&name=univille&phone=123456&city=1"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/Institution"));
		
	    this.mockMvc.perform(get("/Institution")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("univille"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("descricao"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("rua"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("123456"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[5]/text()").string("teste@teste"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[6]/text()").string("Joinville"));

	}
	
	@Test
	public void institutionControllerUpdateTest() throws Exception {
		
		
		City c = new City();	
		c.setName("Joinville");
		c.setState("SC");
		
		cityRepository.save(c);
		cityRepository.flush();
		
		this.mockMvc.perform(get("/Institution/alterar/1")).andDo(print()).andExpect(status().isOk()).andDo(print())
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("univille"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("descricao"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("rua"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("123456"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[5]/text()").string("teste@teste"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[6]/text()").string("Joinville"));
		
		this.mockMvc.perform(post("/Institution")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=1&address=rua&description=descricao&email=teste@teste&name=univille&phone=123456&city=1"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/Institution"));
			    
  }
	@Test
	public void consultAccessories() throws Exception {
		this.mockMvc.perform(get("/catalogo")).andDo(print()).andExpect(status().isOk());
	}
}
