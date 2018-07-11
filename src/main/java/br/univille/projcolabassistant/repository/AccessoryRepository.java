package br.univille.projcolabassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.model.Category;

@Repository
public interface AccessoryRepository extends JpaRepository<AssistiveAccessory, Long>{
	
	public List<AssistiveAccessory> findByCategory(Category category);

}
