angular.module('auctions.message')

.service(
		"MessageService",
		function($resource, SERVER_IP_ADDRESS) {
			return $resource('http://' + SERVER_IP_ADDRESS.ipAddress
					+ '/auctions/messages/:messageId', {
				messageId : '@messageId'
			}, {
				get : {
					method : 'GET'
				},
				delete : {
					method : 'DELETE'
				},
				save : {
					method : 'POST',
					transformRequest : function(data, headers) {
						headers = angular.extend({}, headers, {
							'Content-Type' : 'application/json'
						});
						console.log(data);
						console.log(angular.toJson(data));
						return angular.toJson(data); // this will go in the body request
					}
				},

			});

		});