package com.mastercloudapps.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartValidator {

    static Logger logger = LoggerFactory.getLogger(ShoppingCartValidator.class);

    private ShoppingCartValidator() {
    }

    public static boolean isValid(Long id) {
        logger.info(String.format("Simulating validation for shopping cart with id: %d", id));
        return Math.random() > 0.5;
    }

}
