package br.univille.projcolabassistant.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.univille.projcolabassistant.repository.UserRepository;
import br.univille.projcolabassistant.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<SimpleGrantedAuthority> listGrants = new ArrayList<>();
        listGrants.add(new SimpleGrantedAuthority(user.getType()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),listGrants);
    }
}