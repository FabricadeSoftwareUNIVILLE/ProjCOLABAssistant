package br.univille.projcolabassistant.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;

@Repository
public interface ConsultAccessoriesRepository extends JpaRepository<AssistiveAccessory, Long>{
	/*
	 * select * from (
		(select * from category) as c, (select * from assistive_accessory ) as a
		) where a.category_id = c.id
		*/
	//@Query("select NEW br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel(a,cor) from AssistiveAccessory a, IN(a.colorList) cor order by a.category")
	@Query("select NEW br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel(listA,c) from Category c, (select a from AssistiveAccessory a where a.category = c) listA ")
	public List<AssistiveAccessoryViewModel> findAllAssistiveAccessoryViewModel();

}
