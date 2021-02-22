package com.mastercloudapps.shop.unitary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;
import com.mastercloudapps.shop.domain.dto.ShoppingCartDto;
import com.mastercloudapps.shop.domain.port.ProductUseCase;
import com.mastercloudapps.shop.domain.port.ProductUseCaseImpl;
import com.mastercloudapps.shop.domain.port.ShoppingCartUseCase;
import com.mastercloudapps.shop.domain.port.ShoppingCartUseCaseImpl;
import com.mastercloudapps.shop.domain.repository.ProductRepository;
import com.mastercloudapps.shop.domain.repository.ShoppingCartRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingCartTests {
    
    private ShoppingCartUseCase shoppingCartUseCase;

    private ProductUseCase productUseCase;

    private ShoppingCartRepository shoppingCartRepository;

    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        this.shoppingCartRepository = Mockito.mock(ShoppingCartRepository.class);
        this.shoppingCartUseCase = new ShoppingCartUseCaseImpl(shoppingCartRepository);
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.productUseCase = new ProductUseCaseImpl(productRepository);
    }

    @Test
    void testCreateShoppingCartAndAddProduct() {

        Long cartId = 1L;
        Long prodId = 1L;
        Integer quantity = 3;
        List<FullShoppingCartProductDto> products = new ArrayList<>();
        List<FullShoppingCartProductDto> productsNonEmpty = new ArrayList<>();
        ProductDto productDto = new ProductDto("Product example");

        when(shoppingCartRepository.save(any(ShoppingCartDto.class))).thenReturn(
            new FullShoppingCartDto(cartId, products, false)
        );

        when(productRepository.save(productDto)).thenReturn(new FullProductDto(prodId, productDto.getName()));

        FullShoppingCartDto createdShoppingCart = this.shoppingCartUseCase.createShoppingCart();
        FullProductDto createdProduct = this.productUseCase.createProduct(productDto);
        FullShoppingCartProductDto fullShoppingCartProduct = new FullShoppingCartProductDto(
            createdShoppingCart.getId(), 
            createdProduct.getId(), 
            quantity);

        productsNonEmpty.add(fullShoppingCartProduct);

        when(shoppingCartRepository.addProductToShoppingCartByIds(cartId, prodId, quantity)).thenReturn(
            Optional.of(new FullShoppingCartDto(cartId, productsNonEmpty, false))
        );

        Optional<FullShoppingCartDto> shoppingCartWithProduct = this.shoppingCartUseCase
            .addProductToShoppingCart(cartId, prodId, quantity);

        assertThat(createdShoppingCart.getId(), is(cartId));
        assertThat(createdShoppingCart.getProducts(), is(products));
        assertThat(shoppingCartWithProduct.get().getId(), is(cartId));
        assertThat(shoppingCartWithProduct.get().getProducts(), is(productsNonEmpty));
        assertThat(shoppingCartWithProduct.get().getProducts().get(0), is(fullShoppingCartProduct));
        verify(shoppingCartRepository, times(1)).save(any(ShoppingCartDto.class));
        verify(productRepository, times(1)).save(productDto);
        verify(shoppingCartRepository, times(1)).addProductToShoppingCartByIds(cartId, prodId, quantity);
    }

}
