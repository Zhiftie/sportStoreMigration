import cartLine from'../models/cartLine';
var broker = require ('../messageBroker/rabbitConnector');

function calculateTotal(cartLines) {
    let total = 0;
    cartLines.forEach(cartLine => {
        let price = cartLine.Product.Price;
        
        total += (price*cartLine.Quantity);
    });

    return total;
}

exports.getCart = (req, res) => {
        cartLine.findById(req.body.cartId, (err, cartLine) => {
            if (err) {
                res.send(err);
            }
            res.json(cartLine)
        })
};

exports.addCartLine = (req, res) => {
    const newCartLine = new cartLine(req.body);
    newCartLine.save((err, cartLine)=>{
        if(err) {
            res.send(err);
        }
        res.send("OK 200");
    })
};

exports.getCartLine = (req, res) => {
    console.log(req.body.cartLineId);
    cartLine.findOne({'cartLineId': req.body.cartLineId }, (err, cartLine) => {
        if (err) {
            res.send(err);
        }
        res.json(cartLine);
    })
}

exports.updateCartLine = (req, res) => {
    cartLine.findOneAndUpdate({'cartLineId': req.body.cartLineId}, (err, cartLine)=> {
        if(err) {
            res.send(err)
        }
        res.send("OK 200");
    })
}

exports.checkoutCart =  (req, res) => {
    var content = req.body;
    content.totalCost = calculateTotal(content.Lines);
    content.customerId = 123123;
    broker.sendToQueue("ORDER_CREATE", content);
    res.send("OK 200");
}



