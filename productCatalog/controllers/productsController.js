import mongoose from 'mongoose'; 
import product from '../models/productModel.js';
import tenantManager from '../utils/tenantManager'


exports.getProduct = (req, res) => {
    var query = {
        productId : Number(req.params.productId)
    }
    console.log(query)
    product.find(query, (err, product) => {
        if (err) {
            res.send(err);
        }

        res.json(product);
    });
};

exports.getAllProducts = (req, res) => {
    tenantManager.getUserClaims(req.headers.authorization).then(response => {
        console.log(response);
    }).catch(err => {
        console.log(err)
    })
    product.find({}, (err, products) => {
        if (err) {
            res.send(err);
        }

        res.json(products);
    });
};

exports.createProduct = (req, res) => {
    const newProduct = new product(req.body);

    newProduct.save((err, product) => {
        if (err) {
            res.send(err);
        }

        res.json(product);
    });
};

exports.updateProduct = (req, res) => {
    product.findOneAndUpdate({
        _id: req.params.productId
    }, req.body,
        (err, product) => {
            if (err) {
                res.send(err);
            }

            res.json(product);
        });
};

exports.deleteProduct = (req, res) => {
    product.remove({
        _id: req.params.productId
    }, (err) => {
        if (err) {
            res.send(err);
        }

        res.json({
            message: `product ${req.params.productId} successfully deleted`
        });
    });
};

exports.getCategory = (req, res) => {
    console.log(req.body);
    product.find({
        category: req.body.category}
        , (err, products) => {
            if(err) {
                res.send(err);
            }
            res.json(products);
        });
}; 