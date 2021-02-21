package com.mastercloudapps.shop.domain.port;

import java.util.ArrayList;
import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.dto.ShoppingCartDto;
import com.mastercloudapps.shop.domain.repository.ShoppingCartRepository;

public class ShoppingCartUseCaseImpl implements ShoppingCartUseCase {

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartUseCaseImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public FullShoppingCartDto createShoppingCart() {
        return shoppingCartRepository.save(new ShoppingCartDto(new ArrayList<>()));
    }

    @Override
    public Optional<FullShoppingCartDto> findShoppingCartById(Long id) {
        return shoppingCartRepository.findShoppingCartById(id);
    }

    @Override
    public Optional<FullShoppingCartDto> finishShoppingCartById(Long id) {
        return shoppingCartRepository.finishShoppingCartById(id);
    }

    @Override
    public Optional<FullShoppingCartDto> deleteShoppingCartById(Long id) {
        return shoppingCartRepository.deleteShoppingCartById(id);
    }

    @Override
    public Optional<FullShoppingCartDto> addProductToShoppingCart(Long cartId, Long prodId, Integer quantity) {
        return shoppingCartRepository.addProductToShoppingCartByIds(cartId, prodId, quantity);
    }

    @Override
    public Optional<FullShoppingCartDto> deleteProductFromShoppingCart(Long cartId, Long prodId) {
        return shoppingCartRepository.deleteProductFromShoppingCart(cartId, prodId);
    }
    
}
