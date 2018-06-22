package br.univille.projcolabassistant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.UserRepository;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjColabAssistantApplicationTests {
	
	@Test
	public void contextLoads() {
	}
	
	/*
	@InjectMocks
	private UserReportServiceImpl userReportService;

    @Mock
    UserRepository mockUserRepository;
    
    User dummyUserPedro;
    User dummyUserAna;
    User dummyUserJoao;
    
    private final String EXPECTED_FILES = "src/test/java/expected-files-test/";
	
    @Before
    public void setUp() {
    	dummyUserPedro = new User();
    	
		dummyUserPedro.setId(11111);
		dummyUserPedro.setName("Pedro Barbosa");
		dummyUserPedro.setEmail("pedro@test.com");
		dummyUserPedro.setType("tipo1");
		dummyUserPedro.setPhone("(47) 1111-2222");
		dummyUserPedro.setAddress("Av. Macieira - 777");
		dummyUserPedro.setEnabled(true);
		
		dummyUserAna = new User();
    	
		dummyUserAna.setId(22222);
		dummyUserAna.setName("Ana Barbosa");
		dummyUserAna.setEmail("ana@test.com");
		dummyUserAna.setType("tipo2");
		dummyUserAna.setPhone("(47) 3333-4444");
		dummyUserAna.setAddress("Av. Laranjeiras - 888");
		dummyUserAna.setEnabled(true);
		
		dummyUserJoao = new User();
    	
		dummyUserJoao.setId(33333);
		dummyUserJoao.setName("Joao Silva");
		dummyUserJoao.setEmail("joao@test.com");
		dummyUserJoao.setType("tipo2");
		dummyUserJoao.setPhone("(47) 5555-6666");
		dummyUserJoao.setAddress("Av. Bananeira - 999");
		dummyUserJoao.setEnabled(true);
    }
    
    @Test
    public void getAllUsersReportTest() {
		when(mockUserRepository.searchWithFilters("", "", "")).thenReturn(Arrays.asList(dummyUserPedro, dummyUserAna, dummyUserJoao));
		
		File returnedFile = userReportService.generateUserReport("", "", "");
		File expectedFile = new File(EXPECTED_FILES + "user_report_all_expected.csv");
		
		String returnedContent = readFile(returnedFile.getPath(), StandardCharsets.UTF_8);
		String expectedContent = readFile(expectedFile.getPath(), StandardCharsets.UTF_8);
		
		System.out.println("EQUALS: " + returnedContent.equals(expectedContent));
		
		assertEquals(expectedContent, returnedContent);
		
		returnedFile.delete();
    }
    
    @Test
    public void getReportsFilteredByNameTest() {
		when(mockUserRepository.searchWithFilters("Barbosa", "", "")).thenReturn(Arrays.asList(dummyUserPedro, dummyUserAna));
		
		File returnedFile = userReportService.generateUserReport("Barbosa", "", "");
		File expectedFile = new File(EXPECTED_FILES + "user_report_pedro_ana_expected.csv");
		
		String returnedContent = readFile(returnedFile.getPath(), StandardCharsets.UTF_8);
		String expectedContent = readFile(expectedFile.getPath(), StandardCharsets.UTF_8);
		
		assertEquals(expectedContent, returnedContent);
		
		returnedFile.delete();
    }
    
    @Test
    public void getReportsFilteredByTypeTest() {
		when(mockUserRepository.searchWithFilters("", "", "tipo2")).thenReturn(Arrays.asList(dummyUserAna, dummyUserJoao));
		
		File returnedFile = userReportService.generateUserReport("", "", "tipo2");
		File expectedFile = new File(EXPECTED_FILES + "user_report_ana_joao_expected.csv");
		
		String returnedContent = readFile(returnedFile.getPath(), StandardCharsets.UTF_8);
		String expectedContent = readFile(expectedFile.getPath(), StandardCharsets.UTF_8);
		
		assertEquals(expectedContent, returnedContent);
		
		returnedFile.delete();
    }
    
    @Test
    public void getZeroUsersReportTest() {
		when(mockUserRepository.searchWithFilters("Nonexistent User", "", "")).thenReturn(new ArrayList<User>());
		
		File returnedFile = userReportService.generateUserReport("Nonexistent User", "", "");
		File expectedFile = new File(EXPECTED_FILES + "user_report_nothing_expected.csv");
		
		String returnedContent = readFile(returnedFile.getPath(), StandardCharsets.UTF_8);
		String expectedContent = readFile(expectedFile.getPath(), StandardCharsets.UTF_8);
		
		assertEquals(expectedContent, returnedContent);
		
		returnedFile.delete();
    }
	
	private String readFile(String path, Charset encoding) {
		try {
			byte[] fileContentByte = Files.readAllBytes(Paths.get(path));
			
			return new String(fileContentByte, encoding);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return "";
		}
	}
	*/
}
