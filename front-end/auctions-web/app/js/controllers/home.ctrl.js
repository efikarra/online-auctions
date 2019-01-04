angular.module('auctions').controller('HomeCtrl',
		function($scope, UserService) {

			$scope.recommendedItems = [];
			if ($scope.isAuthenticated) {
				$scope.recommendedItems = UserService.recommendations({}, {
					userId : $scope.userId
				});
			}
		});