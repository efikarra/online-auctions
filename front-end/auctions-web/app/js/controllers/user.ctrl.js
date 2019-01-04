angular.module('auctions.user').controller('UserDetailsCtrl',
		function($scope, UserService,$stateParams) {
			$scope.user = UserService.get({}, {
				userId : $stateParams.userId
			});
			$scope.enableUser = function() {
				$scope.user.enabled = true;
				UserService.enable({
					userId : $stateParams.userId
				}, $scope.user.enabled)
			};
			$scope.disableUser = function() {
				$scope.user.enabled = false;
				UserService.enable({
					userId : $stateParams.userId
				}, $scope.user.enabled)
			};
		}).controller('UsersListCtrl', function(UserService, $scope) {
	$scope.users = UserService.list({enabled:false});

});