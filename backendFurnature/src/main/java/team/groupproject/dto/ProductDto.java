package team.groupproject.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String sku;
    private BigDecimal discount;
    private int stock;
    private String category;
    private String style;

    private List<String> productImagePaths;
    private List<MaterialDto> materials;

    public ProductDto(Integer id, String name, String description, BigDecimal price, String sku, BigDecimal discount,
            int stock, String category, String style, List<String> productImagePaths, List<MaterialDto> materials) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sku = sku;
        this.discount = discount;
        this.stock = stock;
        this.category = category;
        this.style = style;

        this.productImagePaths = productImagePaths;
        this.materials = materials;
    }

    public List<String> getProductImagePaths() {
        return productImagePaths;
    }

    public void setProductImagePaths(List<String> productImagePaths) {
        this.productImagePaths = productImagePaths;
    }

    public List<MaterialDto> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialDto> materials) {
        this.materials = materials;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
