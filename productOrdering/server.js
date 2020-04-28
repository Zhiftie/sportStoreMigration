import mongoose from 'mongoose';

var rabbitConsumer = require('./consumers/productOrderConsumer');
const db = mongoose.connect('mongodb://localhost:27017/orders', {useNewUrlParser: true});
rabbitConsumer.openConnection(db);