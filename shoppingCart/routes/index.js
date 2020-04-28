import cart from '../controllers/cartController';

export default (app) => {
    app.route('/cart-api/cart')
        .get(cart.getCart)
        .post(cart.addCartLine);
    app.route('/cart-api/cartline')
        .get(cart.getCartLine)
        .put(cart.updateCartLine)
    app.route('/cart-api/checkout')
        .post(cart.checkoutCart)
}