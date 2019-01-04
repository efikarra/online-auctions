 angular.module('auctions.category')
 .service("CategoryService", function ($resource,SERVER_IP_ADDRESS) {
        return $resource('http://'+SERVER_IP_ADDRESS.ipAddress+'/auctions/categories', {}, {
            list: {
                method: 'GET',
                isArray: true
            }
        });
    });