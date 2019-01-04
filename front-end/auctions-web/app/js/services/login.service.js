angular.module('auctions.login',[])

.service('AuthService', function ($q, $http, $resource, USER_ROLES,SERVER_IP_ADDRESS) {
	var LOCAL_TOKEN_KEY = 'yourTokenKey';
	var username = '';
	var token_role = '';
	var isAuthenticated = false;
	var role = '';
	var userId='';
	var authToken;

	function loadUserCredentials() {
		var token = window.localStorage.getItem(LOCAL_TOKEN_KEY);
		console.log(token);
		if (token) {
			useCredentials(token);
		}
	}

	function storeUserCredentials(token) {
		console.log(LOCAL_TOKEN_KEY + " " + token);
		window.localStorage.setItem(LOCAL_TOKEN_KEY, token);
		useCredentials(token);
	}

	function useCredentials(token) {
		splittedToken = token.split(':');
		username = splittedToken[0];
		userId=splittedToken[1];
		token_role = splittedToken[2];
		isAuthenticated = true;
		authToken = token;

		if (token_role == 'ROLE_USER') {
			role = USER_ROLES.user
		}
		if (token_role == 'ROLE_ADMIN') {
			role = USER_ROLES.admin
		}
		// Set the token as header for your requests!
		console.log(token);
		$http.defaults.headers.common['X-Auth-Token'] = token;

		console.log($http.defaults.headers.common['X-Auth-Token']);
	}

	function destroyUserCredentials() {
		authToken = undefined;
		username = '';
		userId='';
		role='';
		token_role = '';
		isAuthenticated = false;
		$http.defaults.headers.common['X-Auth-Token'] = undefined;
		window.localStorage.removeItem(LOCAL_TOKEN_KEY);
	}
	var login = function (username, passw) {

		data = {
				username: username,
				password: passw
		};
		return $resource("https://"+SERVER_IP_ADDRESS.httpsIpAddress+"/auctions/authenticate").save({}, data).$promise.then(function (token) {
			storeUserCredentials(token.token);
		});

	};
	var logout = function () {
		destroyUserCredentials();
	};
	var isAuthorized = function(authorizedRoles) {
	    if (!angular.isArray(authorizedRoles)) {
	      authorizedRoles = [authorizedRoles];
	    }
	    return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
	  };
	loadUserCredentials();
	return {
		login: login,
		logout: logout,
		isAuthenticated: function () {
			return isAuthenticated;
		},
		isAuthorized: isAuthorized,
		username: function () {
			return username;
		},
		role: function () {
			return role;
		},
		userId: function () {
			return userId;
		}
	};
})
.factory('AuthInterceptor', function ($rootScope, $q, AUTH_EVENTS,SERVER_IP_ADDRESS) {
	return {
		responseError: function (response) {
			if(response.config.url!="https://"+SERVER_IP_ADDRESS.httpsIpAddress+"/auctions/authenticate") {
				$rootScope.$broadcast({
					401: AUTH_EVENTS.notAuthenticated,
					403: AUTH_EVENTS.notAuthorized
				}[response.status], response);

			}
			return $q.reject(response);
		}
	};
})

.config(function ($httpProvider) {
	$httpProvider.interceptors.push('AuthInterceptor');
});