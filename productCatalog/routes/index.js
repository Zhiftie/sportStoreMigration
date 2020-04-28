import product from '../controllers/productsController';

export default (app) => {
    app.route('/products')
        .get(product.getAllProducts)
        .post(product.createProduct);

    app.route('/products/:productId')
        .get(product.getProduct)
        .put(product.updateProduct)
        .delete(product.deleteProduct);
    app.route('/category/')
        .get(product.getCategory);
};