angular.module('auctions.login')
.controller('LoginCtrl',function($state,$scope,AuthService,$window){
	  $scope.loginData = {};

      $scope.login = function () {
          AuthService.login($scope.loginData.username, $scope.loginData.password).then(function (authenticated) {

              $scope.setCurrentUsername($scope.loginData.username);
              $scope.setCurrentUserId(AuthService.userId());
              
              $scope.setCurrentUserRole(AuthService.role());
              $scope.setAuthenticated(AuthService.isAuthenticated());
              if(AuthService.role()=="ROLE_USER")
            	  {
            	     console.log(AuthService.role());
                     $state.go('main.home.search');
            	  }
              else if(AuthService.role()=="ROLE_ADMIN")
            	  {
            	  $state.go('main.admin');
            	  }

          }, function (err) {
              var alertPopup = $window.alert({
                  title: 'Login failed!',
                  template: 'Please check your credentials!'
              });
          });
      };


});