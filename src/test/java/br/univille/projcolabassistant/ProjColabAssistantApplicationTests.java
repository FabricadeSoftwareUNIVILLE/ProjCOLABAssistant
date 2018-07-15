package br.univille.projcolabassistant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.univille.projcolabassistant.controller.CategoryController;
import br.univille.projcolabassistant.controller.InstitutionController;
import br.univille.projcolabassistant.controller.UserController;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.repository.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjColabAssistantApplicationTests {
	
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private MockMvc mockMvc;


	@Autowired
	private InstitutionController InstitutionController;
	@Autowired
	private CityRepository cityRepository; 

	@Autowired
	private UserController controller;
	
	@Test
	public void contextLoads() {
		//Verifica a existência da instância do controlador

		assertThat(InstitutionController).isNotNull();
		assertThat(controller).isNotNull();
	}
	
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
