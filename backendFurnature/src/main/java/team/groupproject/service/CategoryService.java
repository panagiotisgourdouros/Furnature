package team.groupproject.service;

import java.util.List;
import java.util.Optional;
import team.groupproject.entity.Category;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findByName(String name);

    Optional<Category> findById(Integer id);

    Category updateCategory(Integer id, String newName);

    void deleteCategory(Category category);

    Category saveCategory(Category category);

}
