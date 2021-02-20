package com.mastercloudapps.shop.controller;

import java.net.URI;
import java.util.Collection;

import com.mastercloudapps.shop.controller.dto.request.ProductRequestDto;
import com.mastercloudapps.shop.controller.dto.response.ProductResponseDto;
import com.mastercloudapps.shop.controller.exception.ProductNotFoundException;
import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping("/products")
    public Collection<ProductResponseDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto product) {
        
        FullProductDto fullProduct = productService.save(product);

        ProductResponseDto productResponseDto = new ProductResponseDto(
            fullProduct.getId(), 
            fullProduct.getName());
        
        URI location = fromCurrentRequest().path("/{id}")
            .buildAndExpand(fullProduct.getId()).toUri();

        return ResponseEntity.created(location).body(productResponseDto);

    }

    @DeleteMapping("/products/{id}")
    public ProductResponseDto deleteProductById(@PathVariable Long id) {

        ProductResponseDto product = productService.findById(id).orElseThrow(ProductNotFoundException::new);
        productService.deleteById(id);
        return product;

    }

}
