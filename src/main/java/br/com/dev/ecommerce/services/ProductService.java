package br.com.dev.ecommerce.services;

import br.com.dev.ecommerce.dto.CategoryDTO;
import br.com.dev.ecommerce.dto.ProductDTO;
import br.com.dev.ecommerce.dto.ProductMinDTO;
import br.com.dev.ecommerce.entities.Category;
import br.com.dev.ecommerce.entities.Product;
import br.com.dev.ecommerce.exceptions.DatabaseException;
import br.com.dev.ecommerce.exceptions.ResourceNotFoundException;
import br.com.dev.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return mapper.map(product, ProductDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {

        Page<Product> result = repository.searchByName(name, pageable);

        return result.map(product -> mapper.map(product, ProductMinDTO.class));

    }


    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);
    }


    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try {

            Product entity = repository.getReferenceById(id);

            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);

            return new ProductDTO(entity);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado com ID: " + id);
        }
        try {
            repository.deleteById(id);

        } catch (DataIntegrityViolationException e) {

            throw new DatabaseException("Falha de integridade referencial ao excluir o recurso com ID: " + id);
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();

        for (CategoryDTO catDto : dto.getCategories()) {
            Category cat = new Category();
            cat.setId(catDto.getId());
            entity.getCategories().add(cat);
        }
    }

}