package br.com.dev.ecommerce.services;

import java.time.Instant;

import br.com.dev.ecommerce.dto.OrderDTO;
import br.com.dev.ecommerce.dto.OrderItemDTO;
import br.com.dev.ecommerce.entities.Order;
import br.com.dev.ecommerce.entities.OrderItem;
import br.com.dev.ecommerce.entities.Product;
import br.com.dev.ecommerce.entities.User;
import br.com.dev.ecommerce.enums.OrderStatus;
import br.com.dev.ecommerce.exceptions.ResourceNotFoundException;
import br.com.dev.ecommerce.repositories.OrderItemRepository;
import br.com.dev.ecommerce.repositories.OrderRepository;
import br.com.dev.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;


    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {

        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());

        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();

        order.setClient(user);

        for (OrderItemDTO itemDto : dto.getItems()) {

            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        repository.save(order);

        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
