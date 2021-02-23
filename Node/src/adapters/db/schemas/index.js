const productSchema = require('./Product');
const shoppingCartSchema = require('./ShoppingCart');

const create = (mongoose) => {

    return {
        Product: productSchema(mongoose),
        ShoppingCart: shoppingCartSchema(mongoose),
    };
};

module.exports = { create };
