var config = require('./config.json')
var axios = require('axios')

async function getUserClaims(token) {
    var url = config.identityServer.baseUrl + config.identityServer.endpoints.getClaims
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
    getUserClaims : getUserClaims
}