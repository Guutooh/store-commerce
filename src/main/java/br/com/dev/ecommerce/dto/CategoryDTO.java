package br.com.dev.ecommerce.dto;


import br.com.dev.ecommerce.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


	private Long id;
	private String name;

	public CategoryDTO(Category entity) {
		id = entity.getId();
		name = entity.getName();
	}


}
