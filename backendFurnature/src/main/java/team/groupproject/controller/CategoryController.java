package team.groupproject.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Category;
import team.groupproject.errorHandling.APIException;
import team.groupproject.repository.CategoryRepo;
import team.groupproject.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepo categoryRepo;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping("/{name}")
    public ResponseEntity<Category> createCategory(@PathVariable String name) {
        Optional<Category> categoryOpt = categoryService.findByName(name);

        if (categoryOpt.isPresent()) {
            throw new APIException(String.format("Category with Name : %s already exists", name));

        } else {
            Category category = categoryService.saveCategory(new Category(name));
            return ResponseEntity.ok(category);
        }

    }

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @PathVariable String newName) {
        Category category = categoryService.updateCategory(id, newName);

        if (category != null) {
            return ResponseEntity.ok(category);

        } else {
            throw new APIException(String.format("Category with Id : %d does not exist", id));
        }
    }

}
