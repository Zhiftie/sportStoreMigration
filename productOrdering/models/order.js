import mongoose, {
    Schema
} from 'mongoose';

var Order = new Schema({
    OrderID: {
        type: Number, 
        id: true,
    },
    Lines: {
        type: Array,
    },
    customerId: {
        type: String,
        required: true
    },
    totalCost: {
        type: Number
    },
    shippingInfo: {
        type: Object
    }
},
{collection: 'order'})

export default mongoose.model('order', Order);