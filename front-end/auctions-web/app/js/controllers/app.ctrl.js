angular.module('auctions')
.controller('ModalNotAuthorizedCtrl', function ($scope, $modalInstance) {
	  $scope.okNotAuthorizedModal = function () {
	    $modalInstance.close();
	  };

	})
	.controller('ModalNotAuthenticatedCtrl', function ($scope, $modalInstance) {
	  $scope.okNotAuthenticatedModal = function () {
	    $modalInstance.close();
	  };

	})
.controller('AppCtrl',function($state,$scope,AuthService,$rootScope,AUTH_EVENTS, $modal){
	$state.transitionTo('main.home.search');
	$scope.username = AuthService.username();
	 $scope.userId =AuthService.userId();
		 $scope.role =AuthService.role();
			 $scope.isAuthenticated = AuthService.isAuthenticated();
	$scope.setCurrentUsername = function (name) {
        $scope.username = name;
    };
    $scope.setCurrentUserId = function (userId) {
        $scope.userId = userId;
    };
    $scope.setCurrentUserRole = function (role) {
        $scope.role = role;
    };
    $scope.setAuthenticated = function (isAuthenticated) {
        $scope.isAuthenticated = isAuthenticated;
    };
   
    $scope.$on(AUTH_EVENTS.notAuthorized, function(event) {
		var modalInstance = $modal.open({
		      templateUrl: 'templates/notAuthorizedModal.html',
		      controller: 'ModalNotAuthorizedCtrl',
		      size:'sm'
		      });
      });
     
      $scope.$on(AUTH_EVENTS.notAuthenticated, function(event) {
        AuthService.logout();
        var modalInstance = $modal.open({
		      templateUrl: 'templates/notAuthenticatedModal.html',
		      controller: 'ModalNotAuthenticatedCtrl',
		      size:'sm'
		      });
		modalInstance.result.then(function () {
		     $state.go('main.login');
		});
      });
});