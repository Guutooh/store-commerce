package br.com.dev.ecommerce.dto;


import br.com.dev.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMinDTO {

    private Long id;

    private String name;

    private Double price;

    private String imgUrl;

    public ProductMinDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imgUrl = product.getImgUrl();
    }
}
