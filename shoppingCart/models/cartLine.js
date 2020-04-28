import mongoose, {
    Schema
} from 'mongoose';

var CartLine = new Schema({
    cartLineId: {
        type: Number, 
        id: true,
    },
    productId: {
        type: Number,
    },
    quantity: {
        type: Number
    }
},
{collection: 'cart'})

export default mongoose.model('cart', CartLine);