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

import br.univille.projcolabassistant.controller.RegisterController;
import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.UserRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrarTerapeutaTest {


    @Autowired
    private RegisterController registerController;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
	private UserRepository userRepository;
    
    @Test
    public void contextLoads() {
        //Verifica a existência da instância do controlador
             assertThat(registerController).isNotNull();
    }
    

    @Test
    public void registerController() throws Exception {
        //Teste do método index
        this.mockMvc.perform(get("/user")).andExpect(status().isOk()).andDo(print())
        .andExpect(xpath("/html/body/div/div/table").exists());
    }
    
    @Test
    public void registerControllerSaveTest() throws Exception {
    	
    	City c = new City();
    	c.setName("Joinville");
    	c.setState("SC");
    	
    	cityRepository.save(c);
    	cityRepository.flush();
    	
    	userRepository.deleteAll();
    	
        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("form", "")
                .content("id=0&name=Bruno Henrique Cristofolini&email=bruno@gmail.com&phone=(47) 9 8897-7354&address=Rua Tajsdsaj&city=1"))
                .andDo(print())
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/"));
        
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("Bruno Henrique Cristofolini"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("bruno@gmail.com"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[3]/text()").string("(47) 9 8897-7354"))
        .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[4]/text()").string("Rua Tajsdsaj"));
        
        cityRepository.deleteAll();
        cityRepository.flush();
 
    }



}