package br.univille.projcolabassistant.service.impl;

import br.univille.projcolabassistant.model.City;
import br.univille.projcolabassistant.repository.CityRepository;
import br.univille.projcolabassistant.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City create(City category) {
        return repository.save(category);
    }

    @Override
    public City update(City city) {
        return repository.save(city);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<City> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
