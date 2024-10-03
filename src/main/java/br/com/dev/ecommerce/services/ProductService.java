package br.com.dev.ecommerce.services;

import br.com.dev.ecommerce.dto.ProductDTO;
import br.com.dev.ecommerce.entities.Product;
import br.com.dev.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}
