package br.univille.projcolabassistant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.Institution;


@Repository
public interface InstitutionRepository extends JpaRepository <Institution, Long> {
	@Query("SELECT x FROM Institution x WHERE "
			+ "x.name  LIKE %:nameFilter% AND "
			+ "x.email LIKE %:emailFilter% AND "
			+ "x.city.name LIKE %:cityFilter% "
			+ "ORDER BY x.name DESC")
    public List<Institution> searchWithFiltersByDesc(@Param("nameFilter") String nameFilter, 
    		                                   @Param("emailFilter") String emailFilter,
    		                                   @Param("cityFilter") String cityFilter);
	
	@Query("SELECT x FROM Institution x WHERE "
			+ "x.name  LIKE %:nameFilter% AND "
			+ "x.email LIKE %:emailFilter% AND "
			+ "x.city.name LIKE %:cityFilter% "
			+ "ORDER BY x.name ASC")
    public List<Institution> searchWithFiltersByAsc(@Param("nameFilter") String nameFilter, 
    		                                   @Param("emailFilter") String emailFilter,
    		                                   @Param("cityFilter") String cityFilter);
}

