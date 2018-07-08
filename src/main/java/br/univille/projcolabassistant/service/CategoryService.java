package br.univille.projcolabassistant.service;

import br.univille.projcolabassistant.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category create(Category category);

    Category update(Category category);

    List<Category> findAll();

    Optional<Category> findById(long id);

    void delete(long id);
}
