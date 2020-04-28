// Require express and create an instance of it
var express = require('express');
var app = express();
var db = require('./tenantManager')

// on the request to root (localhost:3000/)
app.get('/', function (req, res) {
    res.send('<b>My</b> first express http server');
});

// On localhost:3000/welcome
app.get('/welcome', function (req, res) {
    var tenant = db.getTenant();
    console.log(tenant);
    res.send(db.getTenant());
});

app.post('/welcome', function(req, res){
    console.log(req);
    res.send("Hello there");
});
 
// Change the 404 message modifing the middleware
app.use(function(req, res, next) {
    res.status(404).send("Sorry, that route doesn't exist. Have a nice day :)");
});

// start the server in the port 3000 !
app.listen(5000, function () {
    console.log('Example app listening on port 5000.');
});
