var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/tenants";
var tenant;

module.exports.mongoConnector = function () {
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("tenants");
        dbo.collection("Tenant").findOne({}, function(err, result) {
          if (err) throw err;
          tenant = result;
        });
      });
    return tenant; 
}