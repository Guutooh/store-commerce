package br.com.dev.ecommerce.entities.auth;

import br.com.dev.ecommerce.services.ProductService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role extends ProductService implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    private String authority;
    /*
     * Não deu problema na implementação porque o nome do atributo já está igual ao da interface.
     * Não precisa do @Override porque a interface GrantedAuthority já possui apenas o método
     * getAuthority() e o Lombok através do @Data já gera esse método com a mesma assinatura.
     */

//    @Override
//    public String getAuthority() {
//        return authority;
//    }
}