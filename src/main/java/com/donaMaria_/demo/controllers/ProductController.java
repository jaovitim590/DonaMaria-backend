package com.donaMaria_.demo.controllers;

import com.donaMaria_.demo.Dtos.ReqProductDto;
import com.donaMaria_.demo.Dtos.ResProductDto;
import com.donaMaria_.demo.Dtos.UpdateProductDto;
import com.donaMaria_.demo.models.Product;
import com.donaMaria_.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
            this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<ResProductDto> res = service.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ReqProductDto data){
        try{
            Product res = service.createProduct(data);
            return ResponseEntity.ok(res);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("erro ao criar produto" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            service.deleteProduct(id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDto data){
        try{
            Product res = service.updateProduct(id, data);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
