const mapper = require('../../mapper');
const shoppingCartModel = require('../../../domain/shoppingCart/model');

async function create() {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.create({ status: 'started' });

        if (!shoppingCart) {
            throw new Error("Shopping cart can't be created.");
        }

        return mapper.mapToDomain(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function finish({ id }, orderValidatorRepository) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const isValidated = orderValidatorRepository.validateOrder({ id }) ? 'finished' : 'started';
        const shoppingCart = await shoppingCartSchema.updateOne({ _id: id }, { $set: { status: isValidated } });

        if (!shoppingCart) {
            throw new Error(`Shopping cart ${_id} can't be updated.`);
        }

        return mapper.mapToDomain(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function get({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.findOne({ _id: id });

        if (!shoppingCart) {
            throw new Error(`Shopping cart ${_id} not found.`);
        }

        return mapper.mapToDomain(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function remove({ id }) {
    try {
        const { ShoppingCart: shoppingCartSchema } = this.getSchemas();
        const shoppingCart = await shoppingCartSchema.findOne({ _id: id });

        if (!shoppingCart) {
            throw new Error(`Shopping cart ${id} not found.`);
        }

        const deleted  = await shoppingCartSchema.deleteOne({ _id: shoppingCart.id });
        
        if (deleted.deletedCount === 0) {
            throw new Error(`Shopping cart ${id} can't be removed.`);
        }

        return mapper.mapToDomain(shoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function addProduct({ shoppingCartId, productId, quantity }) {
    try {
        const { ShoppingCart: shoppingCartSchema, Product: productSchema } = this.getSchemas();
        const product = await productSchema.findOne({ _id: productId });

        if (!product) {
            throw new Error(`Product ${productId} not found. Can't be added to the shopping cart ${shoppingCartId}`);
        }

        const shoppingCart = await shoppingCartSchema.findOne({ _id: shoppingCartId });

        if (!shoppingCart) {
            throw new Error(`Product ${productId} can't be added to a not found shopping cart ${shoppingCartId}`);
        }

        const index = shoppingCart.items.findIndex((item) => item.productId == productId);

        const updatedShoppingCart = { items: [...shoppingCart.items], status: shoppingCart.status };
        if (index !== -1 && quantity > 0) {
            updatedShoppingCart.items[index].quantity += quantity;
        }

        if (index === -1) {
            updatedShoppingCart.items.push({
                productId,
                quantity
            });
        }

        const result = await shoppingCartSchema.updateOne({ _id: shoppingCartId }, updatedShoppingCart);

        if (!result) {
            throw new Error(`Product ${productId} can't be added to shopping cart ${shoppingCartId} due to db error`);
        }

        return mapper.mapToDomain({ ...updatedShoppingCart, _id: shoppingCartId }, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

async function deleteProduct({ shoppingCartId, productId }) {
    try {
        const { ShoppingCart: shoppingCartSchema, Product: productSchema } = this.getSchemas();
        const product = await productSchema.findOne({ _id: productId });

        if (!product) {
            throw new Error(`Product ${productId} not found. Can't be deleted from the shopping cart ${shoppingCartId}`);
        }

        const shoppingCart = await shoppingCartSchema.findOne({ _id: shoppingCartId });

        if (!shoppingCart) {
            throw new Error(`Product ${productId} can't be removed from a non existent shopping cart ${shoppingCartId}`);
        }

        const index = shoppingCart.items.findIndex((item) => item.productId == productId);

        if (index === -1) {
            return null;
        }

        const result = await shoppingCartSchema.updateOne({ _id: shoppingCartId }, { "$pull": { "items": { "productId": productId } }});
        const updatedShoppingCart = await shoppingCartSchema.findOne({ _id: shoppingCartId });

        if (!result) {
            throw new Error(`Product ${productId} can't be removed from shopping cart ${shoppingCartId} due to db error`);
        }
        return mapper.mapToDomain(updatedShoppingCart, shoppingCartModel);
    } catch (error) {
        throw error;
    }
}

const shoppingCartStore = {
    create,
    finish,
    get,
    remove,
    addProduct,
    deleteProduct
};

function init({ ShoppingCart, Product }) {
    return {
        ...shoppingCartStore,
        getSchemas() {
            return { ShoppingCart, Product };
        }
    };
}

module.exports = { init };
