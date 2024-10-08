package br.com.dev.ecommerce.controllers;

import br.com.dev.ecommerce.dto.ProductDTO;
import br.com.dev.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));

    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {

        return ResponseEntity.ok(service.findAll(pageable));
    }


    @PostMapping()
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {


        ProductDTO savedDto = service.insert(dto);

        //Retorna no header o link do objeto criado.
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedDto);

        //return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {


        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
