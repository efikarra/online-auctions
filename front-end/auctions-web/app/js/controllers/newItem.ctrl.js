angular
		.module('auctions.item')

		.controller(
				'NewItemCtrl',
				function($scope, ItemService,$state) {
					$scope.minDate = new Date();
					$scope.maxDate = new Date(2020, 5, 22);
					$scope.includeCoords = false;
					$scope.locationSelected = false;
					$scope.restrictedLocation = {};
					$scope.locationCoords = {
							lat : 37.9433958291991,
							lng : 23.74944269657135
						};
					$scope.startedDate = new Date();
					$scope.endsDate = new Date();
					$scope.startedTime = new Date();
					$scope.endsTime = new Date();
					$scope.newItem = {};
					$scope.newItem.seller = {};
					$scope.newItem.location = {};
					$scope.newItem.categories = [];
					$scope.obj = {};
					$scope.obj.flow;
					$scope.sItem={};
					$scope.saveItem = function() {
						$scope.newItem.seller.userId = $scope.userId;
						$scope.newItem.started = new Date($scope.startedDate
								.getFullYear(), $scope.startedDate.getMonth(),
								$scope.startedDate.getDate(),
								$scope.startedTime.getHours(),
								$scope.startedTime.getMinutes(),
								$scope.startedTime.getSeconds()).getTime();
						$scope.newItem.ends = new Date($scope.endsDate
								.getFullYear(), $scope.endsDate.getMonth(),
								$scope.endsDate.getDate(), $scope.endsTime
										.getHours(), $scope.endsTime
										.getMinutes(), $scope.endsTime
										.getSeconds()).getTime();
						$scope.newItem.location.country = $scope.restrictedLocation.country;
						$scope.newItem.location.locationName = $scope.restrictedLocation.location;
						if ($scope.includeCoords) {
							$scope.newItem.location.latitude = $scope.locationCoords.lat;
							$scope.newItem.location.longitude = $scope.locationCoords.lng;
						}
						$scope.sItem = ItemService.save($scope.newItem).$promise
								.then(
										function(data) {
											console.log($scope.obj.flow.files.length);
											console.log(data.itemId);
											for (var i=0; i < $scope.obj.flow.files.length; i++) {
												var fd = new FormData();
												fd.append('file',$scope.obj.flow.files[i].file);
												ItemService.savePhoto({
													id1 : data.itemId
												}, fd);
											}
											$state.transitionTo('main.home.search');
										}, function(err) {

										});
					};

				});