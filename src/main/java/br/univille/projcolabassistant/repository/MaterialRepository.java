package br.univille.projcolabassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{
	public Material findByName(String name);
}