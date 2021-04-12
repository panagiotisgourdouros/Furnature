package team.groupproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team.groupproject.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

}
