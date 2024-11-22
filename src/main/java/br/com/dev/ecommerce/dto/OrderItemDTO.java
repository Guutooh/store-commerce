package br.com.dev.ecommerce.dto;

import br.com.dev.ecommerce.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

	private Long productId;

	private String name;

	private Double price;

	private Integer quantity;

	private String imgUrl;

	public OrderItemDTO(OrderItem entity) {
		productId = entity.getProduct().getId();
		name = entity.getProduct().getName();
		price = entity.getPrice();
		quantity = entity.getQuantity();
		imgUrl = entity.getProduct().getImgUrl();
	}

	public Double getSubTotal() {
		return price * quantity;
	}

}
