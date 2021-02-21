package com.mastercloudapps.shop.unitary;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;
import com.mastercloudapps.shop.domain.port.ProductUseCase;
import com.mastercloudapps.shop.domain.port.ProductUseCaseImpl;
import com.mastercloudapps.shop.domain.repository.ProductRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProductTests {

    private ProductUseCase productUseCase;

    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.productUseCase = new ProductUseCaseImpl(productRepository);
    }

    @Test
    void testCreateAndDeleteProduct() {

        ProductDto productDto = new ProductDto("Product example");
        Long id = 1L;

        when(productRepository.save(productDto)).thenReturn(new FullProductDto(id, productDto.getName()));
        doNothing().when(productRepository).deleteProductById(id);
        
        FullProductDto createdProductBeforeDeletion = this.productUseCase.createProduct(productDto);
        this.productUseCase.deleteProductById(id);

        assertThat(createdProductBeforeDeletion.getId(), is(id));
        assertThat(createdProductBeforeDeletion.getName(), is(productDto.getName()));
        verify(productRepository, times(1)).save(productDto);
        verify(productRepository, times(1)).deleteProductById(id);
    }
}
