package br.com.dev.ecommerce.repositories;

import br.com.dev.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, Long> {

}
