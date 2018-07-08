package br.univille.projcolabassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter%")
    public List<User> searchWithFilters(@Param("nameFilter") String nameFilter, 
    		                            @Param("emailFilter") String emailFilter);
	
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter% AND "
			+ "u.type = :typeFilter")
    public List<User> searchWithFiltersWithStatus(@Param("nameFilter") String nameFilter, 
    		                                      @Param("emailFilter") String emailFilter,
    		                                      @Param("typeFilter") String typeFilter);
	
}
