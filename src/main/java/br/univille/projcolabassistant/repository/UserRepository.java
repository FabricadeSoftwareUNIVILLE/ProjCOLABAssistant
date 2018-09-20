package br.univille.projcolabassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter% "
			+ "ORDER BY u.name ASC")
    public List<User> searchWithFilters(@Param("nameFilter") String nameFilter, 
    		                            @Param("emailFilter") String emailFilter);
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter% "
			+ "ORDER BY u.name DESC")
    public List<User> searchWithFiltersDesc(@Param("nameFilter") String nameFilter, 
    		                            	@Param("emailFilter") String emailFilter);
	
	
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter% AND "
			+ "u.type = :typeFilter "
			+ "ORDER BY u.name ASC"
			)
    public List<User> searchWithFiltersWithStatus(@Param("nameFilter") String nameFilter, 
    		                                      @Param("emailFilter") String emailFilter,
    		                                      @Param("typeFilter") String typeFilter);
	@Query("SELECT u FROM User u WHERE "
			+ "u.name  LIKE %:nameFilter% AND "
			+ "u.email LIKE %:emailFilter% AND "
			+ "u.type = :typeFilter "
			+ "ORDER BY u.name DESC"
			)
    public List<User> searchWithFiltersWithStatusDesc(@Param("nameFilter") String nameFilter, 
    		                                      @Param("emailFilter") String emailFilter,
    		                                      @Param("typeFilter") String typeFilter);

}
