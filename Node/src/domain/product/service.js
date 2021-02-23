function init({ productRepository }) {
    return {
        getAll: () => productRepository.getAll(),
        create: (product) => productRepository.create(product),
        get: (id) => productRepository.get(id),
        remove: (id) => productRepository.remove(id)
    };
}

module.exports = { init };