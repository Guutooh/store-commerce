package br.com.dev.ecommerce.repositories;

import br.com.dev.ecommerce.entities.Category;
import br.com.dev.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj " +
            "where UPPER (obj.name) LIKE  UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);

    @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories")
    List<Product> findProductsCategory(Category category);

}
