function init({ orderValidatorRepository }) {
    return {
        validate: (orderId) => orderValidatorRepository.validate(orderId)
    };
}

module.exports = { init };