package br.univille.projcolabassistant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.User;

public interface CityRepository extends JpaRepository<City, Long>{
	public City findByName(String name);
}	
