package com.donaMaria_.demo.services;

import com.donaMaria_.demo.Dtos.ReqProductDto;
import com.donaMaria_.demo.Dtos.ResProductDto;
import com.donaMaria_.demo.Dtos.UpdateProductDto;
import com.donaMaria_.demo.exceptions.RecursoNaoEncontradoException;
import com.donaMaria_.demo.models.Categories;
import com.donaMaria_.demo.models.Product;
import com.donaMaria_.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public Product createProduct(ReqProductDto productDto){
        Product p = new Product();

        p.setName(productDto.name());
        p.setDescription(productDto.description());
        p.setPrice(productDto.price());
        p.setCategory(Categories.valueOf(productDto.category()));
        p.setAvailable(true);
        p.setFeatured(false);
        p.setCreate_date(Instant.now());

        repository.save(p);

        return p;
    }

    public void deleteProduct(Long id){
        Product p = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("product"));

        repository.delete(p);
    }

    public Product updateProduct(Long id, UpdateProductDto dto) {

        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("product"));

        Optional.ofNullable(dto.name())
                .filter(name -> !name.isBlank())
                .ifPresent(existingProduct::setName);

        Optional.ofNullable(dto.description())
                .filter(description -> !description.isBlank())
                .ifPresent(existingProduct::setDescription);

        Optional.ofNullable(dto.price())
                .ifPresent(existingProduct::setPrice);

        Optional.ofNullable(dto.category())
                .ifPresent(existingProduct::setCategory);

        return repository.save(existingProduct);
    }

    public List<ResProductDto> getAllProducts(){
        return  repository.findAll()
                .stream()
                .map(product -> new ResProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory(),
                        product.isAvailable(),
                        product.isFeatured()
                ))
                .toList();
    }

    public void turnProduct(Long id){
        Product p = repository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("product"));

        if (p.isAvailable()){
            p.setAvailable(false);
            repository.save(p);
        }else {
            p.setAvailable(true);
            repository.save(p);
        }
    }

    public Long countProducts(){
        return repository.count();
    }

}
