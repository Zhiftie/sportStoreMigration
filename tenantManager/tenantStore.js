var store = {
    "MI6.org" :{
        cached: true,
        endpoints: {
            getProducts: "localhost:8080/getproducts",
            calculateTotalCost: "localhost:8080/calculateTotal",
            
        }
    },
    "omegalul.com": {
        cached: false,
        endpoints:{

        }
    }
}

module.exports = {
    tenants: store
}