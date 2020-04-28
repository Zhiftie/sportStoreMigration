import mongoose, {
    Schema
} from 'mongoose';
import { url } from 'inspector';

/**
 * Create database scheme for products
 */
const ProductScheme = new Schema({
    productID: {
        type: Number,
        id: true,
        generated: true
    },
    name: {
        type: String,
        required: "name?"
    },
    description: {
        type: String,
        required: "Description?"
    },
    price: {
        type: Number,
        required: true
    },
    category: {
        type: String
    }
},{collection: 'product'});

export default mongoose.model('product', ProductScheme);