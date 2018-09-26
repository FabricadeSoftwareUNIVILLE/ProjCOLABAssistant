package br.univille.projcolabassistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.repository.UserRepository;

@Component
public class StartupEventListenerBean {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		City city = cityRepository.findByName("Joinville");
		if(city == null) {
			city = new City();
			city.setName("Joinville");
			city.setState("SC");
			cityRepository.save(city);
		} 

		if(userRepository.findByUsername("user") == null) {
			User user = new User();
			user.setName("Usuário comum");
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("user"));
			user.setType("ROLE_USER");
			user.setCity(city);
			userRepository.save(user);
		}

		if(userRepository.findByUsername("admin") == null) {
			User user = new User();
			user.setName("Administrador");
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setType("ROLE_ADMIN");
			user.setCity(city);
			userRepository.save(user);
		}



	}
}