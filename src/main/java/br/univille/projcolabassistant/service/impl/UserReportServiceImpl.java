package br.univille.projcolabassistant.service.impl;

import static br.univille.projcolabassistant.util.Util.randomId;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.service.UserReportService;

@Service
public class UserReportServiceImpl implements UserReportService {
	private final String REPORTS_PATH = "src/main/resources/temporary-persistent-reports/";
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public File generateUserReport(String nameFilter, String emailFilter, String typeFilter) {
		String resultReportPath = REPORTS_PATH + "user_" + randomId() + ".csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			List<User> users;
			
			if(typeFilter.isEmpty()) {
				users = this.userRepository.searchWithFilters(nameFilter, emailFilter);
			}
			else {
				users = this.userRepository.searchWithFiltersWithStatus(nameFilter, emailFilter, typeFilter);
			}
			
			writer.write("id;name;email;type;phone;address;enabled\n");
			
			for(User user : users) {
				writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", user.getId(), user.getName(), user.getEmail(), user.getType(), user.getPhone(), user.getAddress(), user.isEnabled()));
			}
			
			return new File(resultReportPath);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
}
