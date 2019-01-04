angular.module('auctions.item')
    .service("ItemService", function ($resource,SERVER_IP_ADDRESS) {
        return $resource('http://'+SERVER_IP_ADDRESS.ipAddress+'/auctions/items/:id1/:action1/:id2/:action2', {
            id1: '@id1',
            id2: '@id2'
        }, {
            search: {
                method: 'GET',
                 params: {
                    action1: 'search'
                },
                isArray: true,
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            listJson: {
                method: 'GET',
                params: {
                    action1: 'json'
                },
                isArray: true,
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            listXml: {
                method: 'GET',
                params: {
                    action1: 'xml'
                },
                headers: {
                    'Content-Type': 'application/xml'
                }

            },
            get: {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            save: {
                method: 'POST',
                 headers: {
                    'Content-Type': 'application/json'
                },
                transformRequest: function (data, headers) {
                    headers = angular.extend({}, headers, {
                        'Content-Type': 'application/json'
                    });
                    console.log(data);
                    console.log(angular.toJson(data));
                    return angular.toJson(data); // this will go in the body request
                }

            },
             update: {
                method: 'PUT',
                  headers: {
                    'Content-Type': 'application/json'
                },
                transformRequest: function (data, headers) {
                    headers = angular.extend({}, headers, {
                        'Content-Type': 'application/json'
                    });
                    console.log(data);
                    console.log(angular.toJson(data));
                    return angular.toJson(data); // this will go in the body request
                }

            },
             updateIsClosed: {
                method: 'PUT',
                 params: {action1: 'isClosed'},
                  headers: {
                    'Content-Type': 'application/json'
                },
                transformRequest: function (data, headers) {
                    headers = angular.extend({}, headers, {
                        'Content-Type': 'application/json'
                    });
                    console.log(data);
                    console.log(angular.toJson(data));
                    return angular.toJson(data); // this will go in the body request
                }

            },
             delete: {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            getPhoto: {
                params: {
                    action1: 'photos'
                },
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            savePhoto: {
                params: {
                    action1: 'photos'
                },
                method: 'POST',
                headers: {
                   /* 'Content-Type': 'multipart/form-data; boundary=HereGoes',
                	'enctype':'multipart/form-data'*/
                	'Content-Type':undefined, enctype:'multipart/form-data'
                }/*,
                transformRequest: function (data, headers) {
                    var fd = new FormData();
                   fd.append("file",data);
                   console.log(fd);
                    return fd; 
                }*/

            },
            bids: {
                params: {
                    action1: 'bids'
                },
                method: 'GET',
                isArray: true,
                headers: {
                    'Content-Type': 'application/json'
                }

            },
            saveBid: {
                params: {
                    action1: 'bids'
                },
                method: 'POST',
                transformRequest: function (data, headers) {
                    headers = angular.extend({}, headers, {
                        'Content-Type': 'application/json'
                    });
                    console.log(data);
                    console.log(angular.toJson(data));
                    return angular.toJson(data); // this will go in the body request
                }

            },
            deleteBid: {
                params: {
                    action1: 'bids'
                },
                method: 'DELETE'
            }
            
        });
    });