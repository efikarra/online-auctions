angular
		.module("auctions.map")
		.controller(
				'MapCtrl',
				function($scope, ReverseGeocodingService,_) {
					$scope.results = [];
					 $scope.choice = {
					            location: ''
					        };
					 //$scope.initialPos={};
						 $scope.initialPos=$scope.$parent.locationCoords;
						 
					
					$scope.currentPos = new google.maps.LatLng(
							parseFloat($scope.initialPos.lat),
							parseFloat($scope.initialPos.lng));
					var mapOptions = {
						center : $scope.currentPos,
						zoom : 5,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
					$scope.map = new google.maps.Map(document
							.getElementById("map"), mapOptions);
					$scope.marker = new google.maps.Marker({
						position : $scope.currentPos,
						map : $scope.map,
						options : function() {
							return {
								draggable : true
							}
						}
					});
					$scope.map.addListener('click', function(e) {
						placeMarkerAndPanTo({lat:e.latLng.lat(),lng:e.latLng.lng()});

					});

					function placeMarkerAndPanTo(latLng) {
						$scope.currentPos = new google.maps.LatLng(
								latLng.lat, latLng.lng);
						$scope.marker.setPosition($scope.currentPos);
						$scope.map.panTo($scope.currentPos);
						$scope.$parent.locationCoords={
								lat : latLng.lat,
								lng : latLng.lng
						};
						ReverseGeocodingService.reverseCoords({
							lat : latLng.lat,
							lng : latLng.lng
						}).then(function(results) {
	
							processResults(results);
						});
					}
					;
					$scope.newLocation = function() {
						$scope.$parent.locationSelected=true;
						$scope.$parent.restrictedLocation = _.findWhere($scope.results, {
			                location: $scope.choice.location
			            });
					}
					function processResults(results) {
						var country;
						var level4;
						var level3;
						var level2;

						var length_1 = 3
						if (results.length < 3)
							length_1 = results.length;
						for (var x = 0; x < length_1; x++) {
							
							for (var y = 0, length_2 = results[x].address_components.length; y < length_2; y++) {
								for (var t = 0; t < results[x].address_components[y].types.length; t++) {
									if (results[x].address_components[y].types[t] == 'country') {
										country = results[x].address_components[y].long_name;
									}
									if (results[x].address_components[y].types[t] == 'administrative_area_level_4') {
										level4 = results[x].address_components[y].long_name;
									}
									if (results[x].address_components[y].types[t] == 'administrative_area_level_3') {
										level3 = results[x].address_components[y].long_name;
									}
									if (results[x].address_components[y].types[t] == 'administrative_area_level_2') {
										level2 = results[x].address_components[y].long_name;
									}
									if (country && level4 && level3 && level2) {
										break;
									}
								}
								
							}
						}
						$scope.results = [];

						$scope.results.push({
							country : country,
							location : ''
						});
						if (level2)
							$scope.results.push({
								country : country,
								location : level2
							});
						if (level3&&!(level3==level2))
							$scope.results.push({
								country : country,
								location : level3
							});
						if (level4&&!(level4==level2)&&!(level4==level3))
							$scope.results.push({
								country : country,
								location : level4
							});
						$scope.$parent.locationSelected=true;
						$scope.$parent.restrictedLocation = _.findWhere($scope.results, {
			                location: $scope.choice.location
			            });
						
					}
					;
				});