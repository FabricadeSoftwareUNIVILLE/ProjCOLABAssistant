package br.univille.projcolabassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.projcolabassistant.model.Institution;


@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>{

}

