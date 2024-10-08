package br.com.dev.ecommerce.services;

import br.com.dev.ecommerce.dto.ProductDTO;
import br.com.dev.ecommerce.entities.Product;
import br.com.dev.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;


    public ProductDTO findById(Long id) {

        Product product = repository.findById(id).get();

        return mapper.map(product, ProductDTO.class);
    }

    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> result = repository.findAll(pageable);

        return result.map(product -> mapper.map(product, ProductDTO.class));

    }

}
