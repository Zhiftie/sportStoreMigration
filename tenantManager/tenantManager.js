const mongodb = require('./mongoConnector');

module.exports.getTenant = function () {
    var tenant;
    tenant = mongodb.mongoConnector();
    return tenant;
}