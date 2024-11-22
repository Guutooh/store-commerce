package br.com.dev.ecommerce.repositories;

import br.com.dev.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
