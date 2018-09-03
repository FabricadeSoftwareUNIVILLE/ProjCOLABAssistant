package br.univille.projcolabassistant.service;

import br.univille.projcolabassistant.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    City create(City city);

    City update(City city);

    List<City> findAll();

    Optional<City> findById(long id);

    void delete(long id);
}
