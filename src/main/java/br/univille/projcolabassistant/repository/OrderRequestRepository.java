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
	
	@Query("SELECT x FROM order_request x WHERE x.order_date >= %:creationDateStart% AND "
			                                 + "x.order_date <= %:creationDateEnd% AND "
			                                 + "x.status = %:status%")
    public List<OrderRequest> searchForCreatedBetweenWithStatus(@Param("creationDateStart") Date creationDateStart,
    												  @Param("creationDateEnd") Date creationDateEnd,
    									              @Param("status") int status);
	
	@Query("SELECT x FROM order_request x WHERE x.order_finsh_date >= %:finishedDateStart% AND "
									         + "x.order_finsh_date <= %:finishedDateEnd% AND "
									         + "x.status = %:status%")
	public List<OrderRequest> searchForFinishedBetweenWithStatus(@Param("finishedDateStart") Date finishedDateStart,
													   @Param("finishedDateEnd") Date finishedDateEnd,
										               @Param("status") int status);
	
	@Query("SELECT x FROM order_request x WHERE x.order_date >= %:creationDateStart% AND "
                                             + "x.order_date <= %:creationDateEnd% AND ")
	public List<OrderRequest> searchForCreatedBetween(@Param("creationDateStart") Date creationDateStart,
													  @Param("creationDateEnd") Date creationDateEnd);
	
	@Query("SELECT x FROM order_request x WHERE x.order_finsh_date >= %:finishedDateStart% AND "
											 + "x.order_finsh_date <= %:finishedDateEnd% AND ")
	public List<OrderRequest> searchForFinishedBetween(@Param("finishedDateStart") Date finishedDateStart,
				                                       @Param("finishedDateEnd") Date finishedDateEnd);
	
}
