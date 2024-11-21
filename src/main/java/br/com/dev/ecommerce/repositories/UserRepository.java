package br.com.dev.ecommerce.repositories;

import br.com.dev.ecommerce.entities.User;
import br.com.dev.ecommerce.repositories.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Consulta SQL nativa que retorna os dados do usuário com suas roles/permissões
     * - username: email do usuário (alias necessário para o UserDetails)
     * - password: senha do usuário
     * - roleId: id da role
     * - authority: nome da permissão
     *
     * JOINs necessários pois:
     * - tb_user_role: tabela associativa entre usuário e roles (N:N)
     * - tb_role: tabela com as roles/permissões
     *
     * WHERE filtra pelo email, que é único e usado como username
     */
    @Query(nativeQuery = true, value = """
        SELECT tb_user.email AS username, 
               tb_user.password, 
               tb_role.id AS roleId, 
               tb_role.authority
        FROM tb_user
        INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
        INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
        WHERE tb_user.email = :email
    """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);


    Optional <User> findByEmail(String email);

}
