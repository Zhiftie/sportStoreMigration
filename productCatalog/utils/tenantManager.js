
var config = require('../config.json')
var axios = require('axios')

async function getUserClaims(token) {
    var url = config.endpoints.tenantManager.baseUrl + config.endpoints.tenantManager.endpoints.getUserClaims
    try {
        const response = await axios.get(url, {
            headers: {
              authorization: token
            }
        });
        return response.data;
      } catch (error) {
        console.log(error);
      }
}

module.exports = {
    getUserClaims: getUserClaims
}