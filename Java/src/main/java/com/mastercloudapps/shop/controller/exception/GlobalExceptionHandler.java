package com.mastercloudapps.shop.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
        return new ErrorResponse("Product not found");
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleShoppingCartNotFoundException(ShoppingCartNotFoundException ex) {
        return new ErrorResponse("Shopping Cart not found");
    }

    @ExceptionHandler(ShoppingCartProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleShoppingCartProductNotFoundException(ShoppingCartProductNotFoundException ex) {
        return new ErrorResponse("Shopping Cart or Product not found");
    }

    @ExceptionHandler(ShoppingCartNotValidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleShoppingCartNotValidException(ShoppingCartNotValidException ex) {
        return new ErrorResponse("Shopping Cart is not valid (products unavailable) and cannot be finished");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleDefaultException(Exception ex) {
        return new ErrorResponse(ex.getMessage());
    }

}
