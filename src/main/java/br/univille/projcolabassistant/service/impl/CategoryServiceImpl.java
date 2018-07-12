package br.univille.projcolabassistant.service.impl;

import br.univille.projcolabassistant.model.Category;
import br.univille.projcolabassistant.repository.CategoryRepository;
import br.univille.projcolabassistant.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
