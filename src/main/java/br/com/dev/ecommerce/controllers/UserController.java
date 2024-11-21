package br.com.dev.ecommerce.controllers;

import br.com.dev.ecommerce.dto.UserDTO;
import br.com.dev.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> findMY() {
        return ResponseEntity.ok(service.getMe());
    }

}
