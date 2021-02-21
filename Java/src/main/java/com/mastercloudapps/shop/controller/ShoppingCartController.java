package com.mastercloudapps.shop.controller;

import java.net.URI;

import com.mastercloudapps.shop.controller.dto.response.ShoppingCartResponseDto;
import com.mastercloudapps.shop.controller.exception.ShoppingCartNotFoundException;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.service.ShoppingCartService;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {
    
    private ShoppingCartService shoppingCartService;
    private ModelMapper mapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ModelMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.mapper = mapper;
    }

    @GetMapping("/shoppingcarts/{id}")
    public ShoppingCartResponseDto findShoppingCartById(@PathVariable Long id) {
        return shoppingCartService.findById(id)
            .orElseThrow(ShoppingCartNotFoundException::new);
    }
    
    @PostMapping("/shoppingcarts")
    public ResponseEntity<ShoppingCartResponseDto> addShoppingCart() {
        FullShoppingCartDto fullShoppingCart = shoppingCartService.save();
        ShoppingCartResponseDto shoppingCart = mapper.map(fullShoppingCart, ShoppingCartResponseDto.class);
        URI location = fromCurrentRequest().path("/{id}")
            .buildAndExpand(fullShoppingCart.getId()).toUri();

        return ResponseEntity.created(location).body(shoppingCart);
    }

    @PatchMapping("/shoppingcarts/{id}")
    public ShoppingCartResponseDto finishShoppingCart(@PathVariable Long id) {
        return shoppingCartService.finishShoppingCart(id)
            .orElseThrow(ShoppingCartNotFoundException::new);
    }

}
