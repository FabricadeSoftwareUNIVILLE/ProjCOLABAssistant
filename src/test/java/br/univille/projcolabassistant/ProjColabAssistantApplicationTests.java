package br.univille.projcolabassistant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjColabAssistantApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void consultAccessories() throws Exception {
		this.mockMvc.perform(get("/catalogo")).andDo(print()).andExpect(status().isOk());
	}
}
