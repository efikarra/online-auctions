angular.module('auctions')
.controller('HeaderCtrl',function($state,$scope,AuthService){
	 $scope.logout = function () {
		 AuthService.logout();
         $scope.setCurrentUsername(AuthService.username);
         $scope.setCurrentUserId(AuthService.userId());
         $scope.setCurrentUserRole(AuthService.role());
         $scope.setAuthenticated(AuthService.isAuthenticated());
         $state.go('main.home.search');
     };
     $scope.goToLogin = function () {
    	 $state.go("main.login");
     };
     $scope.goToRegister = function () {
    	 $state.go("main.register");
     };

});