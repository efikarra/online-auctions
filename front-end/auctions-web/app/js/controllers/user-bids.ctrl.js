angular
		.module('auctions.user-bids')
		.controller('UserBidsCtrl',
				function($scope, UserService, $stateParams) {
			$scope.bids = [];
			$scope.typeOfBid = $stateParams.typeOfBid;
			if ($scope.typeOfBid == 'active') {
				$scope.bids = UserService.bids({
					active : true
				}, {
					userId : $scope.userId
				});
			}
			else{
				$scope.bids = UserService.bids({
					active : false
				}, {
					userId : $scope.userId
				});
			}
		});