package br.com.dev.ecommerce.dto;

import br.com.dev.ecommerce.entities.User;
import br.com.dev.ecommerce.entities.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Long id;

    private String name;

    private String email;

    private String phone;

    private LocalDate birthDate;


    private List<String> roles = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.birthDate = user.getBirthDate();
        for (GrantedAuthority role : user.getRoles()) {
            roles.add(role.getAuthority());
        }
    }
}
