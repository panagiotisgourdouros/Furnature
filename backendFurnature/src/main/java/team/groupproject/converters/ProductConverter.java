package team.groupproject.converters;

import java.util.List;
import java.util.stream.Collectors;
import team.groupproject.dto.MaterialDto;
import team.groupproject.dto.ProductDto;
import team.groupproject.entity.Product;

public class ProductConverter {

    public static ProductDto convertToProductDto(Product product) {

        List<String> productImagePaths = product.getProductsImages().stream().map(p -> p.getImagePath())
                .collect(Collectors.toList());
        List<MaterialDto> materialsList = product.getMaterials().stream()
                .map(m -> new MaterialDto(m.getId(), m.getName())).collect(Collectors.toList());
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getSku(), product.getDiscount(), product.getStock(),
                product.getCategory() != null ? product.getCategory().getName() : "",
                product.getStyle() != null ? product.getStyle().getName() : "",
                productImagePaths, materialsList);

    }
}
