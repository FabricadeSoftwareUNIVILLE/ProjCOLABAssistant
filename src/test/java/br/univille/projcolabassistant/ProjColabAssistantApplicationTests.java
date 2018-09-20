package br.univille.projcolabassistant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.univille.projcolabassistant.controller.AccessoryColorController;
import br.univille.projcolabassistant.controller.AssistiveAccessoryController;
import br.univille.projcolabassistant.controller.CategoryController;
import br.univille.projcolabassistant.controller.CityController;
import br.univille.projcolabassistant.controller.InstitutionController;
import br.univille.projcolabassistant.controller.UserController;
import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.Institution;
import br.univille.projcolabassistant.repository.AccessoryColorRepository;
import br.univille.projcolabassistant.repository.AssistiveAccessoryRepository;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.InstitutionRepository;
import br.univille.projcolabassistant.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjColabAssistantApplicationTests {

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private AccessoryColorController accessoryController;

	@Autowired
	private AssistiveAccessoryController assistiveAccessoryController;

	@Autowired
	private AssistiveAccessoryRepository assistiveAccessoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InstitutionController InstitutionController;

	@Autowired
	private InstitutionRepository InstitutionRepository;

	@Autowired
	private CityRepository cityRepository; 
	@Autowired
	private UserRepository userRepository; 

	@Autowired
	private UserController controller;

	@Autowired
	private CityController cityController;
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AccessoryColorRepository accessorycolorRepository;

	@Autowired
	private AccessoryColorRepository AcessoryColorController;

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() {
		//Verifica a existência da instância do controlador
		assertThat(InstitutionController).isNotNull();
		assertThat(controller).isNotNull();
		assertThat(AcessoryColorController).isNotNull();
		assertThat(cityController).isNotNull();
		assertThat(userController).isNotNull();
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
	public void institutionControllerUpdateTest() throws Exception {

		InstitutionRepository.deleteAll();
		InstitutionRepository.flush();
		cityRepository.deleteAll();
		cityRepository.flush();

		this.mockMvc.perform(get("/institution")).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table").exists());


		City c = new City();	
		c.setName("Joinville2");
		c.setState("SC");

		c = cityRepository.save(c);
		cityRepository.flush();

		this.mockMvc.perform(post("/institution")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&address=rua&description=descricao&email=teste@teste&name=univille&phone=123456&city="+c.getId()))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/institution"));

		List<Institution> listInstitution = InstitutionRepository.findAll();

		this.mockMvc.perform(get("/institution/alterar/" + listInstitution.get(0).getId())).andDo(print()).andExpect(status().isOk()).andDo(print())
		.andExpect(xpath("/html/body/div/div/form/div[1]/input/@value").string("univille"))
		.andExpect(xpath("/html/body/div/div/form/div[2]/input/@value").string("descricao"))
		.andExpect(xpath("/html/body/div/div/form/div[3]/input/@value").string("rua"))
		.andExpect(xpath("/html/body/div/div/form/div[4]/input/@value").string("123456"))
		.andExpect(xpath("/html/body/div/div/form/div[5]/input/@value").string("teste@teste"))
		.andExpect(xpath("/html/body/div/div/form/div[6]/select/option/text()").string("Joinville2"));

		this.mockMvc.perform(post("/institution")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("address=rua&description=descricao&email=teste@teste&name=univille&phone=123456&city=" + c.getId() + "&id=" + listInstitution.get(0).getId()))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/institution"));

		InstitutionRepository.deleteAll();
		InstitutionRepository.flush();
		cityRepository.deleteAll();
		cityRepository.flush();

	}
	@Test
	public void consultAccessories() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void cityController() throws Exception {

		cityRepository.deleteAll();
		cityRepository.flush();

		this.mockMvc.perform(post("/city")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=0&name=Joinville3&state=Santa Catarina"))

		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/city"));




		this.mockMvc.perform(get("/city")).andDo(print()).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("Joinville3"))
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("Santa Catarina"));

		cityRepository.deleteAll();
		cityRepository.flush();


	}


	@Test
	public void AssistiveAccessorySave() throws Exception {

		assistiveAccessoryRepository.deleteAll();

		AssistiveAccessory accessory = new AssistiveAccessory();
		accessory.setId(1);
		accessory.setName("Colher adaptada");
		accessory.setCode("AL001");
		accessory.setDescription("Colher adaptada para auxiliar as pessoas com necessidades especiais.");
		accessory.setFunction("Alimentação");
		accessory.setPrescription("Pessoas com necessidades especiais em sua alimentação");

		assistiveAccessoryRepository.save(accessory);

	}

	@Test
	public void userTest() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void userControllerTest() throws Exception {
		this.mockMvc.perform(get("/user")).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table").exists());
	}

	@Test
	public void userSaveTest() throws Exception {
		userRepository.deleteAll();
		City c = new City();	
		c.setName("Joinville");
		c.setState("SC");

		cityRepository.save(c);
		cityRepository.flush();

		this.mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("form", "")
				.content("id=1&name=waltinho&type=professor&email=waltinho@teste.com&phone=12345678&address=univille&city=1&enabled=true"))
		.andDo(print())
		.andExpect(status().isMovedTemporarily())
		.andExpect(view().name("redirect:/user"));

		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("waltinho"))
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("waltinho@teste.com"))
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("12345678"))
		.andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("univille"));

	}
}
