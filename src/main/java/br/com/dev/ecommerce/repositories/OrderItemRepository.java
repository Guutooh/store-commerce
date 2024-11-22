package br.com.dev.ecommerce.repositories;


import br.com.dev.ecommerce.entities.OrderItem;
import br.com.dev.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
