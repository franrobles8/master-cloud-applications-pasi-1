const ProductRepositoryContainer = require('../src/adapters/repository/product');
const schemasContainer = require('../src/adapters/db/schemas')
const mongoose = require ('mongoose')

const product = { _id: 'ajccdc', name: 'Product example' };
const schemas = createDbProducts();
const productRepository = ProductRepositoryContainer.init(schemas);


function createDbProducts(product) {
    const schemas = schemasContainer.create(mongoose);
    const db = { schemas };

    new db.schemas.Product([product]);

    return schemas;
}


describe ('product repository test', () => {
    beforeEach(() => {
        schemas.Product.create = jest.fn();
        schemas.Product.deleteOne = jest.fn();
        schemas.Product.findOne = jest.fn();
    });
    test('Crear un producto', async () => {
        schemas.Product.create.mockReturnValue(product);

        const response = await productRepository.create('Product example');
        
        expect(response.id).toBe('ajccdc');
        expect(response.name).toBe('Product example');
    }); 
    test('Borrar un producto', async () => {
        schemas.Product.findOne.mockReturnValue(product);
        schemas.Product.deleteOne.mockReturnValue({ ok: 1 });
        
        const response = await productRepository.remove({id :'ajccdc'});

        expect(response.id).toBe('ajccdc');
        expect(response.name).toBe('Product example');
    });
});

