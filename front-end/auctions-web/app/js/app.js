angular.module('auctions', ['flow','checklist-model','timer','auctions.user-bids','auctions.category','auctions.utils','auctions.user-account','auctions.message','auctions.user','auctions.map','angular-humanize-duration','uiGmapgoogle-maps','underscore','ngAnimate','ngResource','ngMessages','ngMatch','auctions.category','auctions.item','auctions.login','auctions.user','auctions.register','auctions.search','ui.router','ui.bootstrap']);
angular.module('auctions.user',[]);
angular.module('auctions.login',[]);
angular.module('auctions.register',[]);
angular.module('auctions.search',[]);
angular.module('auctions.item',[]);
angular.module('auctions.category',[]);
angular.module("auctions.map",[]);
angular.module('auctions.user',[]);
angular.module('auctions.message',[]);
angular.module('auctions.user-account',[]);
angular.module('auctions.utils',[]);
angular.module('auctions.user-bids',[]);
angular.module('auctions')
.run(function ($rootScope, $state, AuthService,AUTH_EVENTS) {	
	 $rootScope.$on('$stateChangeStart', function (event,next, nextParams, fromState) {	 
		    if ('data' in next && 'authorizedRoles' in next.data) {
		      var authorizedRoles = next.data.authorizedRoles;
		      if (!AuthService.isAuthorized(authorizedRoles)) {
		        event.preventDefault();
		        $state.go($state.current, {}, {reload: true});
		        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
		      }
		    }
		    /*if (!AuthService.isAuthenticated()) {
		        if (next.name !== 'main.login') {
		          event.preventDefault();
		          $state.go('main.login');
		        }
		      }*/
		  });
	    
	
});