package br.univille.projcolabassistant;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	public void pacienteControllerTest() throws Exception {
	//Teste do método index
	this.mockMvc.perform(get("/institution")).andDo(print()).andExpect(status().isOk())
	.andExpect(xpath("//table").exists())
	.andExpect(xpath("//td[contains(., 'Zezinho')]").exists());
	}
}
