package br.univille.projcolabassistant.service.impl;

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
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public File generateUserReport() {
		String resultReportPath = "result_report.csv";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultReportPath))) {
			List<User> users = this.userRepository.findAll();
			
			writer.write("id;name;email;type;phone;address;enabled\n");
			
			for(User user : users) {
				writer.write(user.getId() + ";" + user.getName() + ";" + user.getEmail() + ";" + user.getType() + ";" + user.getPhone() + ";" + user.getAddress() + ";" + user.isEnabled());
				writer.write("\n");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
		
		return new File(resultReportPath);
	}
	
}
