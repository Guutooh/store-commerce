package br.com.dev.ecommerce.dto;

import br.com.dev.ecommerce.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }


}
