package team.groupproject.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.ProductsImages;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductsImages, Integer> {

    Optional<ProductsImages> findByImagePath(String imagePath);
}
