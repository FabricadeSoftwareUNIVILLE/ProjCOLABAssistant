package br.univille.projcolabassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.projcolabassistant.repository.ConsultAccessoriesRepository;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;

@Controller
@RequestMapping("/")
public class ConsultAccessoriesController {

	@Autowired
	private ConsultAccessoriesRepository consultAccessoriesRepository;
	
	@GetMapping("")
	public ModelAndView index() {
		List<AssistiveAccessoryViewModel> listAccessory = this.consultAccessoriesRepository.findAllAssistiveAccessoryViewModel();
				
		return new ModelAndView("catalog/accessoryList", "listAccessory", listAccessory);
	}
	@GetMapping("/admin")
	public ModelAndView admin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		for(GrantedAuthority item : auth.getAuthorities()) {
			if(item.getAuthority().equals("ROLE_USER"))
				return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("admin/admin");
	}

}