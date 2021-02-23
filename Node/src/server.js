const { serverPort } = require('./config');
const { databaseConnection } = require('./config');

const appContainer = require('./router/app');

const productRepositoryContainer = require('./adapters/repository/product');
const productServiceContainer = require('./domain/product/service');

const shoppingCartRepositoryContainer = require('./adapters/repository/shoppingCart');
const shoppingCartServiceContainer = require('./domain/shoppingCart/service');

const orderValidatorRepositoryContainer = require('./adapters/repository/orderValidator');
const orderValidatorServiceContainer = require('./domain/orderValidator/service');

const { init: initDatabase, connect: connectDatabase } = require('./adapters/db');

const db = initDatabase()({ databaseConnection });

const productRepository = productRepositoryContainer.init(db.createdSchemas);
const productService = productServiceContainer.init({ productRepository });
const shoppingCartRepository = shoppingCartRepositoryContainer.init(db.createdSchemas);
const orderValidatorRepository = orderValidatorRepositoryContainer.init();
const shoppingCartService = shoppingCartServiceContainer.init({ shoppingCartRepository, orderValidatorRepository });
const orderValidatorService = orderValidatorServiceContainer.init({ orderValidatorRepository });


const services = { productService, shoppingCartService, orderValidatorService };

const app = appContainer(services);

const server = app.listen(serverPort, () => {
    console.log(`Server listening on port: ${serverPort}`);
});

connectDatabase(db);

module.exports = server;
