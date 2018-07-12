package br.univille.projcolabassistant.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.AssistiveAccessory;

@Repository
public interface ConsultAccessoriesRepository extends JpaRepository<AssistiveAccessory, Long>{

}
