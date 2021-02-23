const mapper = require('../../mapper');
const productModel = require('../../../domain/product/model');

async function getAll() {
    try {
        const { Product: productSchema } = this.getSchemas();

        const products = await productSchema.find({});

        if (!products) {
            throw new Error('Products not found');
        }

        return products.map((product) => mapper.mapToDomain(product, productModel));
    } catch (error) {
        throw error;
    }
}

async function get({ id }) {
    try {
        const { Product: productSchema } = this.getSchemas();

        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product ${id} not found.`);
        }

        return mapper.mapToDomain(product, productModel);
    } catch (error) {
        throw error;
    }
}

async function create({ name }) {
    const { Product: productSchema } = this.getSchemas();

    try {
        const product = await productSchema.create({ name });
        if (!product) {
            throw new Error(`Product ${id} not found.`);
        }

        return mapper.mapToDomain(product, productModel);
    } catch (error) {
        throw error;
    }
}

async function remove({ id }) {
    const { Product: productSchema } = this.getSchemas();

    try {
        const product = await productSchema.findOne({ _id: id });

        if (!product) {
            throw new Error(`Product ${id} not found.`);
        }

        const { ok } = await productSchema.deleteOne({ _id: product._id });

        if (!ok) {
            throw new Error(`Product with id: ${id} couldn't be removed.`);
        }

        return mapper.mapToDomain({ _id: id, name: product.name }, productModel);
    } catch (error) {
        throw error;
    }
}

const productStore = {
    getAll,
    get,
    create,
    remove
};

function init({ Product }) {
    return {
        ...productStore,
        getSchemas() {
            return { Product };
        }
    };
}

module.exports = { init };
