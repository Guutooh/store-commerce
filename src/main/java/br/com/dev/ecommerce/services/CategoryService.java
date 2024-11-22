package br.com.dev.ecommerce.services;

import java.util.List;

import br.com.dev.ecommerce.dto.CategoryDTO;
import br.com.dev.ecommerce.entities.Category;
import br.com.dev.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {

        List<Category> result = repository.findAll();

        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }
}
