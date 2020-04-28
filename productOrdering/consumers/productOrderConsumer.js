import Order from '../models/order';

var amqp = require('amqplib/callback_api');


const openConnection = (mongooseConnection) => {
amqp.connect('amqp://localhost', function(error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }
    var exchange = 'sportStore';

    channel.assertExchange(exchange, 'direct',{
      durable: true
    });
    channel.assertQueue('ORDER_CREATE', {
      exclusive: true
    }, function(error2, q) {
      if (error2){
        throw error2;
      }
      console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q);
      channel.bindQueue(q.queue, exchange, "ORDER_CREATE")
      channel.consume(q.queue, function(msg) {
        var body = JSON.parse(msg.content); //Content of the message as json
        
        const order = new Order(body);
        order.shippingInfo = {
          name : body.Name,
          line1 : body.Line1,
          line2 : body.Line2,
          line3 : body.Line3,
          city : body.City,
          state : body.State,
          zip: body.Zip, 
          country : body.Country,
          giftWrap : body.GiftWrap,
          shipped : body.Shipped
        }
        order.save((err)=>{
            if (err) {
                throw err;
            }
        });
    })
  }, {
      // manual acknowledgment mode,
      // see https://www.rabbitmq.com/confirms.html for details
      noAck: false
    });
  });
});
}

module.exports = {
    openConnection: openConnection
}
