// Require express and create an instance of it
var express = require('express');
var app = express();
var identiServer = require('./IdentityServer')
var tenantStore =  require('./tenantStore')

// on the request to root (localhost:3000/)
app.get('/getUserClaims', function (req, res) {
    const tenantData = identiServer.getUserClaims(req.headers.authorization).then(response => {
        return tenantStore.tenants[response.website];
    }).then(data => {res.send(data)})
});

// start the server in the port 3000 !
app.listen(3000, function () {
    console.log('TenantManager listening on port 3000.');
});
