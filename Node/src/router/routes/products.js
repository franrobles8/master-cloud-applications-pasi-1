const express = require('express');
const router = express.Router();

function init({ productService }) {
    router.get('/', async (req, res) => {
        const productList = await productService.getAll();

        return res.send(productList);
    });

    router.post('/', async (req, res) => {
        const newProduct = await productService.create({
            name: req.body.name
        });

        return res.send(newProduct);
    });

    router.get('/:id', async (req, res) => {
        const { id } = req.params;
        const product = await productService.get({ id });

        return res.send(product);
    });

    router.delete('/:id', async (req, res) => {
        const { id } = req.params;
        const result = await productService.remove({ id });

        return res.send(result);
    });

    return router;
}

module.exports = { init };
