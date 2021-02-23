const create = (mongoose) => {
    const productSchema = mongoose.Schema({
        name: {
            type: String,
            required: true
        },
    });

    return mongoose.model('Product', productSchema);
};

module.exports = create;