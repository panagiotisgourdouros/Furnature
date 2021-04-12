package team.groupproject.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.groupproject.converters.ProductConverter;
import team.groupproject.dto.ProductDto;
import team.groupproject.entity.Category;
import team.groupproject.entity.Material;
import team.groupproject.entity.Product;
import team.groupproject.entity.ProductsImages;
import team.groupproject.entity.Style;
import team.groupproject.errorHandling.APIException;
import team.groupproject.repository.CategoryRepo;
import team.groupproject.repository.MaterialRepo;
import team.groupproject.repository.ProductImageRepo;
import team.groupproject.repository.ProductRepo;
import team.groupproject.repository.StyleRepo;
import team.groupproject.service.FileService;
import team.groupproject.service.ProductServices;

import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private static final Logger LOGGER = getLogger(ProductController.class);

    @Autowired
    private ProductServices productService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private StyleRepo styleRepo;

    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private ProductImageRepo productImageRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private FileService fileService;

    @GetMapping
    ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> list = productService.getAllProducts().stream().map(ProductConverter::convertToProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/materials/{id}")
    ResponseEntity<List<ProductDto>> getById(@PathVariable("id") @Min(1) int id) {
        List<ProductDto> list = productService.findByMaterialId(id).stream().map(ProductConverter::convertToProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);

    }

    @GetMapping(value = "/styles/{id}")
    ResponseEntity<List<ProductDto>> getByStyle(@PathVariable int id) {
        List<ProductDto> list = productService.findByStyleId(id).stream().map(ProductConverter::convertToProductDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/categories/{id}")
    ResponseEntity<List<ProductDto>> getByCategory(@PathVariable int id) {
        List<ProductDto> list = productService.findByCategoryId(id).stream()
                .map(ProductConverter::convertToProductDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);

    }

    @PostMapping(value = "/admin/createProduct", consumes = {"multipart/form-data"})
    @Transactional
    RedirectView createNewProduct(
            @RequestParam(required = false) MultipartFile file,
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String sku,
            @RequestParam String description,
            @RequestParam Integer discount,
            @RequestParam(name = "category") Integer categoryId,
            @RequestParam(name = "style") Integer styleId,
            @RequestParam Integer price,
            @RequestParam Integer stock,
            @RequestParam(name = "materials", required = false) List<Integer> materialIds) {

        try {

            List<String> errors = new ArrayList<>();

            Product product;
            if (id != 0) {
                Optional<Product> productOpt = productRepo.findById(id);
                if (productOpt.isPresent()) {
                    product = productOpt.get();
                    //sku must be unique in the database
                    //only if sku carried in dto is different from sku on product we must assign new value and check for uniqueness
                    List<Product> productsWithSku = productRepo.findBySku(sku);
                    if ((sku != null && !product.getSku().equals(sku))
                            || (sku != null && !sku.equals(product.getSku()))) {
                        if (productsWithSku.isEmpty()) {
                            product.setSku(sku);
                        } else {
                            errors.add(String.format("sku : %s already exist for other products", sku));
                        }
                    }
                } else {
                    throw new APIException(String.format("Product to be updated with id :%d does not exist", id));
                }
            } else {
                product = new Product();
                //create a new array list for cartDetails and orderDetails only for newly created Products
                product.setOrdersDetails(new ArrayList<>());
                product.setCartDetails(new HashSet<>());
                product.setDateCreated(new Date());
                product.setProductsImages(new ArrayList<>());
                product.setMaterials(new ArrayList<>());
                //sku must be unique in the database
                List<Product> productsWithSku = productRepo.findBySku(sku);
                if (productsWithSku.isEmpty()) {
                    product.setSku(sku);
                } else {
                    errors.add(String.format("sku : %s already exist for other products", sku));
                }
            }

            product.setDateUpdated(new Date());
            product.setName(name);
            product.setDescription(description);
            product.setPrice(BigDecimal.valueOf(price));
            product.setDiscount(BigDecimal.valueOf(discount));
            product.setStock(stock);

            if (categoryId != null) {
                Optional<Category> categoryOpt = categoryRepo.findById(categoryId);
                if (categoryOpt.isPresent()) {
                    product.setCategory(categoryOpt.get());
                } else {
                    errors.add(String.format("Category with id : %d does not exist", categoryId));
                }
            } else {
                product.setCategory(null);
            }

            if (styleId != null) {
                Optional<Style> styleOpt = styleRepo.findById(styleId);
                if (styleOpt.isPresent()) {
                    product.setStyle(styleOpt.get());
                } else {
                    errors.add(String.format("Style with id : %d does not exist", styleId));
                }
            } else {
                product.setStyle(null);
            }

            LOGGER.info("materials received {}", materialIds);
            List<Material> materials = product.getMaterials();
            //clear existing materials
            materials.clear();
            if (materialIds != null && !materialIds.isEmpty()) {
                materialIds.forEach(materialId -> {
                    Optional<Material> materialOpt = materialRepo.findById(materialId);
                    if (materialOpt.isPresent()) {
                        LOGGER.info("material added {}", materialOpt.get());
                        materials.add(materialOpt.get());
                    } else {
                        errors.add(String.format("Material with id: %d does not exist", materialId));
                    }
                });
            }

            if (file.getSize() > 0) {
                String fileName = file.getOriginalFilename();
                fileService.uploadFile(file);
                //reset what the product holds as image entities so that orphan entities are removed
                product.getProductsImages().add(new ProductsImages("/images/" + fileName));
            }

            if (errors.isEmpty()) {
                product = productRepo.save(product);
                return new RedirectView("http://127.0.0.1:5500/admin-products.html");
            } else {
                throw new APIException(String.join(",", errors));
            }

        } catch (Exception ex) {
            LOGGER.error("Error occurred", ex);
            throw ex;
        }

    }

}
