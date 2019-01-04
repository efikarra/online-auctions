angular.module('auctions.category')
.controller('CategoriesListCtrl',
		function($scope, $http, CategoryService) {
	console.log('cat ctrl');
			$scope.categories = [];
			$scope.categories = CategoryService.list();
			$scope.compareFn = function(obj1, obj2) {
				return obj1.categoryId === obj2.categoryId;
			};

		})
