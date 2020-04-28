var amqp = require('amqplib/callback_api');


module.exports = {
    sendToQueue: function (key, message) {
            amqp.connect('amqp://localhost', function(error0, connection) {
            if (error0) {
                throw error0;
            }
        
            connection.createChannel(function(error1, channel) {
                if (error1) {
                    throw error1;
                }
        
                var exchange = 'sportStore';
                message = JSON.stringify(message);
                channel.assertExchange(exchange, 'direct', {
                    durable: true
                });
                channel.publish(exchange, key, Buffer.from(message));
                console.log(" [x] Sent %s", message)
            })
        
            setTimeout(function(){
                connection.close();
            }, 500)
        })}  
}