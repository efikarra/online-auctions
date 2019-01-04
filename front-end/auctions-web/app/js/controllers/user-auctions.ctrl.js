angular
		.module('auctions.user-account')
		.controller('UserAuctionsCtrl',
				function($scope, UserService, $stateParams) {
					$scope.items = [];
					$scope.typeOfAuction = $stateParams.typeOfAuction;
					if ($scope.typeOfAuction == 'active') {
						$scope.items = UserService.items({
							active : true
						}, {
							userId : $scope.userId
						});
					}
					if ($scope.typeOfAuction == 'inActive') {
						$scope.items = UserService.items({
							active : false
						}, {
							userId : $scope.userId
						});
					}
					if ($scope.typeOfAuction == 'ended') {

						$scope.items = UserService.endedItems({}, {
							userId : $scope.userId
						});
					}
					
				})
		.controller('AuctionDetailsCtrl',
				function($scope, ItemService, $stateParams) {
					$scope.typeOfAuction = $stateParams.typeOfAuction;
					$scope.activeAuction = false;
					$scope.endedAuction = false;
					if ($scope.typeOfAuction == 'active') {
						$scope.activeAuction = true;
					} else {
						$scope.endedAuction = true;
					}
					$scope.auction = ItemService.get({}, {
						id1 : $stateParams.itemId
					});
				})
		.controller(
				'AuctionEditCtrl',
				function($scope, ItemService, $stateParams) {
					$scope.minDate = new Date(1990, 5, 22);
					$scope.maxDate = new Date(2020, 5, 22);
					$scope.startedDate = new Date();
					$scope.endsDate = new Date();
					$scope.startedTime = new Date();
					$scope.endsTime = new Date();
					$scope.editItem = {};
					$scope.editItem.seller = {};
					$scope.editItem.location = {};
					$scope.includeCoords = false;
					$scope.locationSelected = true;
					$scope.restrictedLocation = {};
					$scope.restrictedLocation.country = $scope.editItem.location.country;
					$scope.restrictedLocation.location = $scope.editItem.location.locationName;
					$scope.locationCoords = {
							lat : 37.9433958291991,
							lng : 23.74944269657135
						};
					$scope.editItem = ItemService.get({}, {
						id1 : $stateParams.itemId
					}, function() {
						$scope.minDate =  new Date($scope.editItem.started);
						$scope.maxDate = new Date($scope.editItem.ends);
						$scope.startedDate = new Date($scope.editItem.started);
						console.log($scope.startedDate);
						$scope.endsDate = new Date($scope.editItem.ends);
						$scope.startedTime = new Date($scope.editItem.started);
						$scope.endsTime = new Date($scope.editItem.ends);
						if ($scope.editItem.location.latitude != null)
							$scope.includeCoords = true;
						$scope.locationCoords = {
							lat : $scope.editItem.location.latitude,
							lng : $scope.editItem.location.longitude
						};
						$scope.initialPos = {
							lat : $scope.editItem.location.latitude,
							lng : $scope.editItem.location.longitude
						};
					});

					$scope.updateItem = function() {
						$scope.editItem.started = new Date($scope.startedDate
								.getFullYear(), $scope.startedDate.getMonth(),
								$scope.startedDate.getDate(),
								$scope.startedTime.getHours(),
								$scope.startedTime.getMinutes(),
								$scope.startedTime.getSeconds()).getTime();
						$scope.editItem.ends = new Date($scope.endsDate
								.getFullYear(), $scope.endsDate.getMonth(),
								$scope.endsDate.getDate(), $scope.endsTime
										.getHours(), $scope.endsTime
										.getMinutes(), $scope.endsTime
										.getSeconds()).getTime();
						$scope.editItem.location.country = $scope.restrictedLocation.country;
						$scope.editItem.location.locationName = $scope.restrictedLocation.location;
						if ($scope.includeCoords) {
							$scope.editItem.location.latitude = $scope.locationCoords.lat;
							$scope.editItem.location.longitude = $scope.locationCoords.lng;
						}
						
						$scope.editItem.bids=ItemService.bids({
							id1:$scope.editItem.itemId
						},function(){
							console.log($scope.editItem);
							ItemService.save($scope.editItem,function(){
								$state.transitionTo('main.accountMenu.bids.inActive', $stateParams, { reload: true, inherit: false, notify: true });
							});
						});
						
					};
				});