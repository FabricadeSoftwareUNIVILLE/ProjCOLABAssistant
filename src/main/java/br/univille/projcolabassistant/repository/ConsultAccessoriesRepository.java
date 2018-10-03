package br.univille.projcolabassistant.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.AssistiveAccessory;
import br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel;

@Repository
public interface ConsultAccessoriesRepository extends JpaRepository<AssistiveAccessory, Long>{
	@Query("select NEW br.univille.projcolabassistant.viewmodel.AssistiveAccessoryViewModel(a,photo) from AssistiveAccessory a, IN(a.photoList) photo order by a.category.name")
	public List<AssistiveAccessoryViewModel> findAllAssistiveAccessoryViewModel();
}
