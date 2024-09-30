package br.com.dev.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true) // configurando que será um campo unico
    private String email;

    private String phone;

    private String password;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

}
