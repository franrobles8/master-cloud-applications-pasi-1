const ShoppingCartRepositoryContainer = require('../src/adapters/repository/shoppingCart');
const schemasContainer = require('../src/adapters/db/schemas')
const mongoose = require ('mongoose')

const product = { _id: 'ajccdc', name: 'Product example' };
const cart = { _id: 'adasdsa', items: [], status: 'started' };
const schemas = createDbShoppingCarts();
const shoppingCartRepository = ShoppingCartRepositoryContainer.init(schemas);


function createDbShoppingCarts() {
    const schemas = schemasContainer.create(mongoose);
    const db = { schemas };

    new db.schemas.ShoppingCart();
    new db.schemas.Product([product]);

    return schemas;
}


describe ('Shopping cart repository test', () => {
    beforeEach(() => {
        schemas.ShoppingCart.create = jest.fn();
        schemas.ShoppingCart.findOne = jest.fn();
        schemas.ShoppingCart.updateOne = jest.fn();
        schemas.Product.findOne = jest.fn();
    });
    test('Crear un carrito', async () => {
        schemas.ShoppingCart.create.mockReturnValue(cart);

        const response = await shoppingCartRepository.create();
        
        expect(response.id).toBe(cart._id);
        expect(response.items).toBe(cart.items);
        expect(response.status).toBe(cart.status);
    }); 
    test('Añadir producto a un carrito', async () => {
        schemas.ShoppingCart.create.mockReturnValue(cart);
        schemas.ShoppingCart.findOne.mockReturnValue(cart);
        schemas.Product.findOne.mockReturnValue(product);
        const expectedCart = { _id: 'adasdsa', items: [{productId: product._id, quantity: 3}], status: 'started' }

        schemas.ShoppingCart.updateOne.mockReturnValue(expectedCart);

        const response = await shoppingCartRepository.create();
        
        const updatedResponse = await shoppingCartRepository.addProduct({ shoppingCartId: cart._id, productId: product._id, quantity: 3 });

        expect(updatedResponse.id).toBe(expectedCart._id);
        expect(updatedResponse.items).toEqual(expectedCart.items);
        expect(updatedResponse.status).toBe(expectedCart.status);
    });
});

