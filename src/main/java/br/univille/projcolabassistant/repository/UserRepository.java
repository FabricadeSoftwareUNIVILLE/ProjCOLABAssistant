package br.univille.projcolabassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
