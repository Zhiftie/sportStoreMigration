const csv = require('csvtojson');
const filepath = './data.csv';

var mongoose = require('mongoose');
 
// make a connection
mongoose.connect('mongodb://localhost:27017/products');
 
// get reference to database
var db = mongoose.connection;
 
db.on('error', console.error.bind(console, 'connection error:'));
 
db.once('open', function() {
    console.log("Connection Successful!");
    
    // define Schema
    var ProductSchema = mongoose.Schema({
      name: String,
      cost: Number,
      description: String,
      productId: String,
      category: String
    });
 
    // compile schema to model
    var Product = mongoose.model('Product', ProductSchema, 'product');
 
    csv().fromFile(filepath).then((jsonObj)=>{
        jsonObj.forEach(element => {
            var product = new Product(element)
            product.save(function (err, product) {
                if (err) return console.error(err);
                console.log(product.name + " saved to bookstore collection.");
            });
        })  
    }); 
});
