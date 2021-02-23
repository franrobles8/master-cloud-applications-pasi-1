const express = require('express');
const productRouter = require('./routes/products');
const shoppingCartRouter = require('./routes/shoppingCart');

function init(services) {
    const app = express();

    app.use(express.urlencoded({ extended: false }));
    app.use(express.json());

    app.use('/api/products', productRouter.init(services));
    app.use('/api/shoppingcarts', shoppingCartRouter.init(services));

    return app;
}

module.exports = init;