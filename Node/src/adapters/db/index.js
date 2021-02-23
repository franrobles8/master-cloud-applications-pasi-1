const mongoose = require('mongoose');

const schemas = require('./schemas');

function init() {
    return function ({ databaseConnection }) {
        if (!databaseConnection) {
            throw new Error('databaseConnection not provided');
        }

        mongoose.connection.on('error', (error) => {
            console.log(`Error on connection. ${error.toString()}`);
        });

        mongoose.connection.once('open', () => {
            console.log('Connection open');
        });

        mongoose.connection.on('disconnected', () => {
            console.log('Connection closed');
        });

        const createdSchemas = schemas.create(mongoose);

        return {
            ...{
                getConnection() {
                    return mongoose.connection;
                },
                connect() {
                    return mongoose.connect(databaseConnection, {
                        useNewUrlParser: true,
                        useUnifiedTopology: true
                    });
                },
                close() {
                    return mongoose.connection.close();
                }
            },
            createdSchemas
        };
    };
}

async function connect(db) {
    try {
        await db.connect();
    } catch (error) {
        await db.close();
    }
}

module.exports = { init, connect };