package team.groupproject.service;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import team.groupproject.entity.Product;

@Validated
public interface ProductServices {

    List<Product> getAllProducts();

    Product findById(int id);

    Product save(Product prd);

    void delete(Product product);

    List<Product> findByMaterialId(int id);

    List<Product> findByStyle(String style);

    List<Product> findByCategory(String category);

    List<Product> findByCategoryId(int id);

    List<Product> findByStyleId(int id);
}
