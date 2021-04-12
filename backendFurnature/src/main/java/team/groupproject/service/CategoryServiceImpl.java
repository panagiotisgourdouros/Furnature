package team.groupproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Category;
import team.groupproject.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepo.findById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepo.findByName(name);
    }

    public Category updateCategory(Integer id, String newName) {

        Optional<Category> categoryOpt = categoryRepo.findById(id);

        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            category.setName(newName);
            categoryRepo.save(category);
            return category;
        } else {
            return null;
        }
    }

    public void deleteCategory(Category category) {
        categoryRepo.delete(category);

    }

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

}
