package br.univille.projcolabassistant;



import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import br.univille.projcolabassistant.controller.InstitutionController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjColabAssistantApplicationTests {

	
	@Autowired
	private InstitutionController InstitutionController;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() {
	//Verifica a existência da instância do controlador

	assertThat(InstitutionController).isNotNull();
	}

	
	   @Test
	    public void institutionControllerTest() throws Exception {
	        //Teste do método index
	        this.mockMvc.perform(get("/Institution")).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table").exists());
	    }
	   
	   
	   @Test
	    public void institutionControllerSaveTest() throws Exception {
	        this.mockMvc.perform(post("/Institution")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("form", "")
	                .content("id=0&nome=zezinho&sexo=Masculino"))
	                .andDo(print())
	                .andExpect(status().isMovedTemporarily())
	                .andExpect(view().name("redirect:/Institution"));
	        
	        this.mockMvc.perform(get("/Institution")).andDo(print()).andExpect(status().isOk())
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("zezinho"))
	        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("Masculino"));
	            
	    }
}
