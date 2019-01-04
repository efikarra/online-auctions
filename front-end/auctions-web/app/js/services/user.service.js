angular.module('auctions.user')

.service(
		"UserService",
		function($resource, SERVER_IP_ADDRESS) {
			return $resource('http://' + SERVER_IP_ADDRESS.ipAddress
					+ '/auctions/users/:userId/:action/:id/:action2/:id1', {
				userId : '@userId',
				id : '@id',
				id1 : '@id1'
			}, {
				get : {
					method : 'GET',
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				list : {
					method : 'GET',
					isArray : true,
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				save : {
					method : 'POST',
					url: 'https://' + SERVER_IP_ADDRESS.httpsIpAddress
					+ '/auctions/users',
					transformRequest : function(data, headers) {
						headers = angular.extend({}, headers, {
							'Content-Type' : 'application/json'
						});
						console.log(data);
						console.log(angular.toJson(data));
						return angular.toJson(data); // this will go in the body request
					}
				},
				update : {
					method : 'PUT',
					transformRequest : function(data, headers) {
						headers = angular.extend({}, headers, {
							'Content-Type' : 'application/json'
						});
						console.log(data);
						console.log(angular.toJson(data));
						return angular.toJson(data); // this will go in the body request
					}
				},
				items : {
					params : {
						action : 'items'
					},
					method : 'GET',
					isArray : true,
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				endedItems : {
					params : {
						action : 'items',
						action2: 'ended'
					},
					method : 'GET',
					isArray : true,
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				recommendations : {
					params : {
						action : 'recommendation'
					},
					method : 'GET',
					isArray : true,
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				bids : {
					params : {
						action : 'bids'
					},
					method : 'GET',
					isArray : true,
					headers : {
						'Content-Type' : 'application/json'
					}
				},
				enable : {
					params : {
						action : 'enabled'
					},
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
				receivedMessages : {
					params : {
						action : 'messages',
						action2 : 'receivedMessages'
					},
					method : 'GET',
					isArray : true
				},
				delReceivedMessage : {
					params : {
						action : 'messages',
						action2 : 'receivedMessages'
					},
					method : 'DELETE'
				},
				sentMessages : {
					params : {
						action : 'messages',
						action2 : 'sentMessages'
					},
					method : 'GET',
					isArray : true
				},
				delSentMessage : {
					params : {
						action : 'messages',
						action2 : 'sentMessages'
					},
					method : 'DELETE'
				},
				saveMessage : {
					params : {
						action : 'messages'
					},
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