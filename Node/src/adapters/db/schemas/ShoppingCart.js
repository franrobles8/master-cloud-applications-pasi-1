const create = (mongoose) => {
    const shoppingCartSchema = mongoose.Schema({
        items: {
            type: [{
                productId: {
                    type: mongoose.Schema.Types.ObjectId,
                    ref: 'Product',
                    required: true
                },
                quantity: {
                    type: Number,
                    min: [1, 'Quantity cant be less than one'],
                    required: true
                }
            }],
            default: [],
            required: true
        },
        status: {
            type: String,
            required: true
        }
    });

    return mongoose.model('ShoppingCart', shoppingCartSchema);
};

module.exports = create;
