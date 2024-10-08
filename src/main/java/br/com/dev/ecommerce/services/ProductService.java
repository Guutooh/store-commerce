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

public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product product = repository.findById(id).get();

        return mapper.map(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {

        Page<Product> result = repository.findAll(pageable);

        return result.map(product -> mapper.map(product, ProductDTO.class));

    }

    public ProductDTO insert(ProductDTO dto) {

        Product product = mapper.map(dto, Product.class);

        return mapper.map(repository.save(product), ProductDTO.class);


    }


    public ProductDTO update (Long id, ProductDTO dto) {

        // Busca o produto pelo ID ou lança exceção se não for encontrado
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Mapeia as mudanças do DTO para o produto existente
        mapper.map(dto, product);

        // Salva o produto atualizado no banco de dados
        product = repository.save(product);

        // Retorna o produto atualizado como DTO
        return mapper.map(product, ProductDTO.class);

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}