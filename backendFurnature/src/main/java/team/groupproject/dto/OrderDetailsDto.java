package team.groupproject.dto;

public class OrderDetailsDto {

    private Integer quantity;
    private ProductDto product;

    public OrderDetailsDto(Integer quantity, ProductDto product) {

        this.quantity = quantity;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

}
