require('dotenv').config();

const config = {
    serverPort: process.env.SERVER_PORT || 8080,
    databaseConnection:
        process.env.DB_HOST || 'mongodb://localhost:27017/testdb'
};

module.exports = config;