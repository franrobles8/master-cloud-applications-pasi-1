function validateOrder(order) {
    return Math.random() < 0.5;
}

function init() {
    return {
        validateOrder
    };
}

module.exports = { init };