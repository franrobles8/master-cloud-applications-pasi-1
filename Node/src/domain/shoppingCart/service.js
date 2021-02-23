function init({ shoppingCartRepository, orderValidatorRepository }) {
    return {
        create: () => shoppingCartRepository.create(),
        finish: (id) => shoppingCartRepository.finish(id, orderValidatorRepository),
        get: (id) => shoppingCartRepository.get(id),
        remove: (id) => shoppingCartRepository.remove(id),
        addProduct: (productId) => shoppingCartRepository.addProduct(productId),
        deleteProduct: (productId) => shoppingCartRepository.deleteProduct(productId)
    };
}

module.exports = { init };