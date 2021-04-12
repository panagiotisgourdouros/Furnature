package team.groupproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Material;
import team.groupproject.entity.Product;
import team.groupproject.repository.MaterialRepo;
import team.groupproject.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductServices {

    private ProductRepo productRepo;
    private MaterialRepo materialRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, MaterialRepo materialRepo) {
        this.productRepo = productRepo;
        this.materialRepo = materialRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepo.delete(product);
    }

    @Override
    public List<Product> findByMaterialId(int id) {
        Material material = this.materialRepo.findById(id);
        return material.getProductsList();
    }

    @Override
    public List<Product> findByStyle(String style) {
        return productRepo.findByStyleName(style);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepo.findByCategoryName(category);
    }

    @Override
    public List<Product> findByCategoryId(int id) {
        return productRepo.findByCategoryId(id);
    }

    @Override
    public List<Product> findByStyleId(int id) {
        return productRepo.findByStyleId(id);
    }

}
