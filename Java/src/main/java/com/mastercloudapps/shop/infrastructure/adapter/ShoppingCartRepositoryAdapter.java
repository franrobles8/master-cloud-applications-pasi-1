package com.mastercloudapps.shop.infrastructure.adapter;

import java.util.Optional;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.controller.exception.ProductNotFoundException;
import com.mastercloudapps.shop.controller.exception.ShoppingCartNotFoundException;
import com.mastercloudapps.shop.controller.exception.ShoppingCartProductNotFoundException;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartProductDto;
import com.mastercloudapps.shop.domain.dto.ShoppingCartDto;
import com.mastercloudapps.shop.domain.repository.ShoppingCartProductJpaRepository;
import com.mastercloudapps.shop.domain.repository.ShoppingCartRepository;
import com.mastercloudapps.shop.infrastructure.model.ProductEntity;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartEntity;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartProduct;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartProductId;
import com.mastercloudapps.shop.infrastructure.repository.ProductJpaRepository;
import com.mastercloudapps.shop.infrastructure.repository.ShoppingCartJpaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

    private ModelMapper mapper;
    private ShoppingCartJpaRepository shoppingCartJpaRepository;
    private ProductJpaRepository productJpaRepository;
    private ShoppingCartProductJpaRepository shoppingCartProductJpaRepository;

    public ShoppingCartRepositoryAdapter(
        ModelMapper mapper, 
        ShoppingCartJpaRepository shoppingCartJpaRepository, 
        ProductJpaRepository productJpaRepository,
        ShoppingCartProductJpaRepository shoppingCartProductJpaRepository) {

        this.mapper = mapper;
        this.shoppingCartJpaRepository = shoppingCartJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.shoppingCartProductJpaRepository = shoppingCartProductJpaRepository;
    }

    @Override
    public FullShoppingCartDto save(ShoppingCartDto shoppingCartDto) {
        ShoppingCartEntity shoppingCart = mapper.map(shoppingCartDto, ShoppingCartEntity.class);
        ShoppingCartEntity savedShoppingCartEntity = shoppingCartJpaRepository.save(shoppingCart);
        return mapper.map(savedShoppingCartEntity, FullShoppingCartDto.class);
    }

    @Override
    public Optional<FullShoppingCartDto> findShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(id);
        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    @Override
    public Optional<FullShoppingCartDto> finishShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(id);
        
        if(shoppingCartEntity.isPresent()) {
            ShoppingCartEntity shoppingCart = shoppingCartEntity.get();
            shoppingCart.setCompleted(true);
            shoppingCartJpaRepository.save(shoppingCart);
        }

        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    @Override
    public Optional<FullShoppingCartDto> deleteShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(id);
        
        if(shoppingCartEntity.isPresent()) {
            shoppingCartJpaRepository.deleteById(id);
        }
        
        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    @Override
    public Optional<FullShoppingCartDto> addProductToShoppingCartByIds(Long cartId, Long prodId, Integer quantity) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(cartId);
        Optional<ProductEntity> productEntity = productJpaRepository.findById(prodId);

        if(!shoppingCartEntity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        if(!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(
            shoppingCartEntity.get(), productEntity.get(), quantity);
            shoppingCartEntity.get().getProducts().add(shoppingCartProduct);
            shoppingCartJpaRepository.save(shoppingCartEntity.get());

        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    @Override
    public Optional<FullShoppingCartDto> deleteProductFromShoppingCart(Long cartId, Long prodId) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(cartId);
        Optional<ProductEntity> productEntity = productJpaRepository.findById(prodId);

        if(!shoppingCartEntity.isPresent()) {
            throw new ShoppingCartNotFoundException();
        }

        if(!productEntity.isPresent()) {
            throw new ProductNotFoundException();
        }

        Optional<ShoppingCartProduct> shoppingCartProduct = shoppingCartProductJpaRepository.findById(
            new ShoppingCartProductId(shoppingCartEntity.get().getId(), productEntity.get().getId()));

        if(!shoppingCartProduct.isPresent()) {
            throw new ShoppingCartProductNotFoundException();
        }

        shoppingCartProductJpaRepository.deleteById(shoppingCartProduct.get().getId());

        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    private static FullShoppingCartDto mapToFullShoppingCartDto(ShoppingCartEntity shoppingCart) {
        return new FullShoppingCartDto(
            shoppingCart.getId(), 
            shoppingCart.getProducts()
                .stream()
                .map(p -> 
                    new FullShoppingCartProductDto(p.getShop().getId(), p.getProd().getId(), p.getQuantity())
                )
                .collect(Collectors.toList()), 
            shoppingCart.isCompleted());
    }
    
}
