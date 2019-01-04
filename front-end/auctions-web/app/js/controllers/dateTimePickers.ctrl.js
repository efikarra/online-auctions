angular.module('auctions.utils').controller(
		'DateTimePickersCtrl',
		function($scope) {
			console.log('pickers ctrl');
			$scope.status = {
				startedOpened : false,
				endsOpened : false
			};
			$scope.illegalDateTime = false;
			$scope.minEndDate=$scope.$parent.minDate;
			$scope.$watch('$parent.startedDate', function(v) {
				$scope.minEndDate = v;
				$scope.$parent.endsDate = v;
			});
			$scope.$watch('$parent.endsTime', function(v) {
				var started = new Date(
						$scope.$parent.startedDate.getFullYear(),
						$scope.$parent.startedDate.getMonth(),
						$scope.$parent.startedDate.getDate(),
						$scope.$parent.startedTime.getHours(),
						$scope.$parent.startedTime.getMinutes(),
						$scope.$parent.startedTime.getSeconds()).getTime();
				var ends = new Date($scope.$parent.endsDate.getFullYear(),
						$scope.$parent.endsDate.getMonth(),
						$scope.$parent.endsDate.getDate(),
						$scope.$parent.endsTime.getHours(),
						$scope.$parent.endsTime.getMinutes(),
						$scope.$parent.endsTime.getSeconds()).getTime();
				if (ends < started) {
					$scope.illegalDateTime = true;
				} else
					$scope.illegalDateTime = false;
			});


			$scope.openStarted = function($event) {
				$scope.status.startedOpened = true;
			};
			$scope.openEnds = function($event) {
				$scope.status.endsOpened = true;
			};

			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};

		})
		.controller(
		'DateTimePickersCtrl2',
		function($scope) {
			$scope.status = {
				startedOpened : false,
				endsOpened : false
			};
			$scope.illegalDateTime = false;
			$scope.minEndDate=$scope.$parent.minDate;
			$scope.minDate = $scope.$parent.minDate;
			$scope.maxDate =$scope.$parent.maxDate;
			$scope.$watch('$parent.startedDate', function(v) {
				$scope.minEndDate = v;
				$scope.$parent.endsDate = v;
			});
			$scope.$watch('$parent.endsTime', function(v) {
				var started = new Date(
						$scope.$parent.startedDate.getFullYear(),
						$scope.$parent.startedDate.getMonth(),
						$scope.$parent.startedDate.getDate(),
						$scope.$parent.startedTime.getHours(),
						$scope.$parent.startedTime.getMinutes(),
						$scope.$parent.startedTime.getSeconds()).getTime();
				var ends = new Date($scope.$parent.endsDate.getFullYear(),
						$scope.$parent.endsDate.getMonth(),
						$scope.$parent.endsDate.getDate(),
						$scope.$parent.endsTime.getHours(),
						$scope.$parent.endsTime.getMinutes(),
						$scope.$parent.endsTime.getSeconds()).getTime();
				if (ends < started) {
					$scope.illegalDateTime = true;
				} else
					$scope.illegalDateTime = false;
			});


			$scope.openStarted = function($event) {
				$scope.status.startedOpened = true;
			};
			$scope.openEnds = function($event) {
				$scope.status.endsOpened = true;
			};

			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};

		});;