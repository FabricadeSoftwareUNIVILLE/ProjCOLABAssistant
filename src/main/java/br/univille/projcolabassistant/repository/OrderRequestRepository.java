package br.univille.projcolabassistant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.OrderRequest;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long>{
	
	@Query("SELECT x FROM OrderRequest x WHERE "
			    + "x.orderDate >= :creationDateStart AND "
		        + "x.orderDate <= :creationDateEnd AND "
		        + "x.orderFinshDate >= :finishedDateStart AND "
				+ "x.orderFinshDate <= :finishedDateEnd AND "
				+ "x.status = :status AND "
				+ "x.userRequest.name LIKE %:userName% "
				+ "ORDER BY x.orderDate DESC")
	public List<OrderRequest> searchBetweenWithStatusDesc(@Param("creationDateStart") Date creationDateStart,
												          @Param("creationDateEnd") Date creationDateEnd,
												          @Param("finishedDateStart") Date finishedDateStart,
									                      @Param("finishedDateEnd") Date finishedDateEnd,
									                      @Param("userName") String userName,
									                      @Param("status") int status);
	
	@Query("SELECT x FROM OrderRequest x WHERE "
			    + "x.orderDate >= :creationDateStart AND "
		        + "x.orderDate <= :creationDateEnd AND "
		        + "x.orderFinshDate >= :finishedDateStart AND "
				+ "x.orderFinshDate <= :finishedDateEnd AND "
				+ "x.userRequest.name LIKE %:userName% "
				+ "ORDER BY x.orderDate DESC")
	public List<OrderRequest> searchBetweenDesc(@Param("creationDateStart") Date creationDateStart,
												@Param("creationDateEnd") Date creationDateEnd,
											    @Param("finishedDateStart") Date finishedDateStart,
									            @Param("finishedDateEnd") Date finishedDateEnd,
									            @Param("userName") String userName);
	
	@Query("SELECT x FROM OrderRequest x WHERE "
		    + "x.orderDate >= :creationDateStart AND "
	        + "x.orderDate <= :creationDateEnd AND "
	        + "x.orderFinshDate >= :finishedDateStart AND "
			+ "x.orderFinshDate <= :finishedDateEnd AND "
			+ "x.status = :status AND "
			+ "x.userRequest.name LIKE %:userName% "
			+ "ORDER BY x.orderDate ASC")
	public List<OrderRequest> searchBetweenWithStatusAsc(@Param("creationDateStart") Date creationDateStart,
											              @Param("creationDateEnd") Date creationDateEnd,
											              @Param("finishedDateStart") Date finishedDateStart,
											              @Param("finishedDateEnd") Date finishedDateEnd,
											              @Param("userName") String userName,
											              @Param("status") int status);
	
	@Query("SELECT x FROM OrderRequest x WHERE "
		    + "x.orderDate >= :creationDateStart AND "
	        + "x.orderDate <= :creationDateEnd AND "
	        + "x.orderFinshDate >= :finishedDateStart AND "
			+ "x.orderFinshDate <= :finishedDateEnd AND "
			+ "x.userRequest.name LIKE %:userName% "
			+ "ORDER BY x.orderDate ASC")
	public List<OrderRequest> searchBetweenAsc(@Param("creationDateStart") Date creationDateStart,
											   @Param("creationDateEnd") Date creationDateEnd,
										       @Param("finishedDateStart") Date finishedDateStart,
								               @Param("finishedDateEnd") Date finishedDateEnd,
								               @Param("userName") String userName);

}
