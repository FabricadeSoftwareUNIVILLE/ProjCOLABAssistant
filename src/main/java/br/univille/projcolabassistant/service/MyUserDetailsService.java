package br.univille.projcolabassistant.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.model.User;
import br.univille.projcolabassistant.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}else {
			if(!user.isEnabled()) {
				throw new AccessDeniedException("403 returned");
			}
		}

		Collection<SimpleGrantedAuthority> listGrants = new ArrayList<>();
		listGrants.add(new SimpleGrantedAuthority(user.getType()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),listGrants);
	}

	public Collection<? extends GrantedAuthority> getUserRoles(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities();
	} 

	public User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByUsername(auth.getName());
		return user;
	}

}