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
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderDate >= :creationDateStart AND "
                                            + "x.orderDate <= :creationDateEnd AND "
                                            + "x.status = :status")
	public List<OrderRequest> searchForCreatedBetweenWithStatus(@Param("creationDateStart") Date creationDateStart,
						                                        @Param("creationDateEnd") Date creationDateEnd,
						                                        @Param("status") int status);
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderFinshDate >= :finishedDateStart AND "
									        + "x.orderFinshDate <= :finishedDateEnd AND "
									        + "x.status = :status")
	public List<OrderRequest> searchForFinishedBetweenWithStatus(@Param("finishedDateStart") Date finishedDateStart,
            													 @Param("finishedDateEnd") Date finishedDateEnd,
            													 @Param("status") int status);
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderDate >= :creationDateStart AND "
	            							+ "x.orderDate <= :creationDateEnd")
	public List<OrderRequest> searchForCreatedBetween(@Param("creationDateStart") Date creationDateStart,
			                                          @Param("creationDateEnd") Date creationDateEnd);
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderFinshDate >= :finishedDateStart AND "
				 							+ "x.orderFinshDate <= :finishedDateEnd")
	public List<OrderRequest> searchForFinishedBetween(@Param("finishedDateStart") Date finishedDateStart,
	                                                   @Param("finishedDateEnd") Date finishedDateEnd);
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderDate >= :creationDateStart AND "
			                                + "x.orderDate <= :creationDateEnd AND "
			                                + "x.orderFinshDate >= :finishedDateStart AND "
						 					+ "x.orderFinshDate <= :finishedDateEnd")
	public List<OrderRequest> searchBetween(@Param("creationDateStart") Date creationDateStart,
            								@Param("creationDateEnd") Date creationDateEnd,
            								@Param("finishedDateStart") Date finishedDateStart,
                                            @Param("finishedDateEnd") Date finishedDateEnd);
	
	@Query("SELECT x FROM OrderRequest x WHERE x.orderDate >= :creationDateStart AND "
									        + "x.orderDate <= :creationDateEnd AND "
									        + "x.orderFinshDate >= :finishedDateStart AND "
											+ "x.orderFinshDate <= :finishedDateEnd AND "
											+ "x.status = :status")
	public List<OrderRequest> searchBetweenWithStatus(@Param("creationDateStart") Date creationDateStart,
											          @Param("creationDateEnd") Date creationDateEnd,
											          @Param("finishedDateStart") Date finishedDateStart,
								                      @Param("finishedDateEnd") Date finishedDateEnd,
								                      @Param("status") int status);

}
