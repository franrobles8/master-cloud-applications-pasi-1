const ProductServiceContainer = require('../src/domain/product/service');
const ProductRepositoryContainer = require('../src/adapters/repository/product');

jest.mock(ProductRepositoryContainer);

test('Crear y borrar un producto', () => {

    const expectedProduct = {name: "Product example", _id: "ajccdc"};
    ProductRepositoryContainer.create("Product example").mockReturnValue(expectedProduct)
    ProductRepositoryContainer.remove("ajccdc").mockReturnValue(expectedProduct)

    expect().toBeCloseTo(7);

    expect().toBeCalledWith(1);   

});