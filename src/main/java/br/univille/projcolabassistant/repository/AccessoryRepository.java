package br.univille.projcolabassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.univille.projcolabassistant.model.AssistiveAccessory;

public interface AccessoryRepository extends JpaRepository <AssistiveAccessory, Long> {
	
	@Query("SELECT x FROM AssistiveAccessory x WHERE "
	     + "x.category.name LIKE %:categoryFilter%")
    public List<AssistiveAccessory> searchByCategory(@Param("categoryFilter") String categoryFilter);

}
