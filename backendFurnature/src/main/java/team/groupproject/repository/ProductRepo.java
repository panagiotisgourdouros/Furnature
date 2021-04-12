package team.groupproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.groupproject.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    public Product findById(int id);

    public List<Product> findByStyleName(String style);

    public List<Product> findByCategoryName(String category);

    public List<Product> findBySku(String sku);

    public List<Product> findByStyleId(int id);

    public List<Product> findByCategoryId(int id);
}
