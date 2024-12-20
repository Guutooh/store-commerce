package br.com.dev.ecommerce.services;

import br.com.dev.ecommerce.dto.UserDTO;
import br.com.dev.ecommerce.entities.User;
import br.com.dev.ecommerce.entities.auth.Role;
import br.com.dev.ecommerce.repositories.UserRepository;
import br.com.dev.ecommerce.repositories.projections.UserDetailsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    /**
     * Método da interface UserDetailsService que carrega um usuário pelo username (email)
     * Este método é chamado pelo Spring Security durante o processo de autenticação
     *
     * @param username Email do usuário que está tentando autenticar
     * @return UserDetails Usuário com suas credenciais e permissões
     * @throws UsernameNotFoundException se usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Busca usuário e suas roles através da consulta customizada
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        // Se não encontrou usuário, lança exceção
        if (result.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User: %s not found", username));
        }

        // Cria objeto User que implementa UserDetails
        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());// Pega senha do primeiro resultado (será a mesma em todos)

        // Adiciona todas as roles encontradas para o usuário
        // Um usuário pode ter múltiplas roles, por isso o loop
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user; // Retorna usuário com seus dados e permissões
    }

    protected User authenticated() {

        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return repository.findByEmail(username).get();

        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = authenticated();
        return new UserDTO(user);
    }

}
