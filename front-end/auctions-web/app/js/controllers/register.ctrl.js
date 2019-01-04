angular.module('auctions.register')
.controller('ModalRegistrationCtrl', function ($scope, $modalInstance) {
	  $scope.okRegistrationModal = function () {
	    $modalInstance.close();
	  };

	})
.controller('RegisterCtrl', function ($scope, $state,  _, UserService,$modal) {
	$scope.user = {};
	$scope.errorData = [];
	$scope.saveUser = function () {               
		UserService.save($scope.user).$promise.then(function () {
			var modalInstance = $modal.open({
			      templateUrl: 'templates/confirmRegistrationModal.html',
			      controller: 'ModalRegistrationCtrl',
			      size:'sm'
			      });
			modalInstance.result.then(function () {
			     $state.go('main.home.search');
			});

		}, function (data) {
			$scope.errorData = data.data;
			console.log($scope.errorData);
			var modalInstance2 = $modal.open({
			      templateUrl: 'templates/errorsPopup.html',
			      size:'sm',
			      scope: $scope
			      });

		});

	};
});
