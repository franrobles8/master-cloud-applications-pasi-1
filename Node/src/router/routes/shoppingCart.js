const express = require('express');

const router = express.Router();

function init({ shoppingCartService }) {
    router.post('/', async (req, res) => {
        const shoppingCart = await shoppingCartService.create();
        return res.send(shoppingCart);
    });

    router.patch('/:id', async (req, res) => {
        const { id } = req.params;
        const shoppingCart = await shoppingCartService.finish({ id });
        res.send(shoppingCart);
    });

    router.get('/:id', async (req, res) => {
        const { id } = req.params;
        const shoppingCart = await shoppingCartService.get({ id });
        res.send(shoppingCart);
    });

    router.delete('/:id', async (req, res) => {
        const { id } = req.params;
        const result = await shoppingCartService.remove({ id });
        return res.send(result);
    });

    router.post('/:id/product/:productId/quantity/:quantity', async (req, res) => {
        const { id: shoppingCartId, productId, quantity } = req.params;
        const result = await shoppingCartService.addProduct({ shoppingCartId, productId, quantity: parseInt(quantity, 10) });
        return res.send(result);
    });

    router.delete('/:id/product/:productId', async (req, res) => {
        const { id: shoppingCartId, productId } = req.params;
        const result = await shoppingCartService.deleteProduct({ shoppingCartId, productId });
        return res.send(result);
    });

    return router;
}

module.exports = { init };
