package br.univille.projcolabassistant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.OrderRequest;
import br.univille.projcolabassistant.viewmodel.OrderSumByCategory;

@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long>{
	
	@Query("SELECT x FROM OrderRequest x WHERE "
			    + "x.orderDate >= :creationDateStart AND "
		        + "x.orderDate <= :creationDateEnd AND "
		        + "x.orderFinshDate >= :finishedDateStart AND "
				+ "x.orderFinshDate <= :finishedDateEnd AND "
				+ "x.status = :status AND "
				+ "x.userRequest.name LIKE %:userName%")
	public List<OrderRequest> searchBetweenWithStatus(@Param("creationDateStart") Date creationDateStart,
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
				+ "x.userRequest.name LIKE %:userName%")
	public List<OrderRequest> searchBetween(@Param("creationDateStart") Date creationDateStart,
											@Param("creationDateEnd") Date creationDateEnd,
										    @Param("finishedDateStart") Date finishedDateStart,
								            @Param("finishedDateEnd") Date finishedDateEnd,
								            @Param("userName") String userName);
	
	@Query("SELECT NEW br.univille.projcolabassistant.viewmodel.OrderSumByCategory(c.id,c.name,SUM(o.quantity)) FROM OrderItems o, IN(o.accessory) a, IN(a.category) c  where c.name LIKE %:categoryName% GROUP BY c.id")
	public List<OrderSumByCategory> searchOrderSumByCategory(@Param("categoryName") String categoryName);

}
