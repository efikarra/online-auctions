angular.module('auctions.search')
.controller('SearchCtrl', function ($scope,ItemService,CategoryService,$modal) {
	$scope.items =[];
	$scope.keyword=null;
	$scope.selectedCategory={};
	$scope.minPrice=null;
	$scope.maxPrice=null;
	$scope.locationName=null;
	$scope.enabledSearch=false;
	$scope.searchDone=false;
	$scope.totalItems =0;
	$scope.categories=CategoryService.list(function()
			{
		    var c={};
		    c.categoryName="All categories";
	       	$scope.categories.unshift(c);
	       	$scope.selectedCategory=$scope.categories[0];
	       	$scope.enabledSearch=true;
			});
	
	
        $scope.search = function () {
        	$scope.searchDone=true;
        	if ($scope.keyword ==""||$scope.keyword ==null&&$scope.selectedCategory.categoryName=="All categories"&&$scope.minPrice==null&&$scope.maxPrice==null&&$scope.locationName==null||$scope.locationName=="") {
            $scope.items = ItemService.listJson(function () {
            	 $scope.totalItems= $scope.items.length;
                if (_.isEmpty($scope.items)) {
                	var modalInstance = $modal.open({
      			      template: '<div class="modal-header">'+
	'<h4 class="modal-title">Results</h4>'
      			      +'</div><div class="modal-body"><div>No results found!</div></div>',
      			      controller: 'ModalRegistrationCtrl',
      			      size:'sm'
      			      });
                }
            });

        }
      else {
    	  if ($scope.keyword =="")
    		  $scope.keyword =null;
            $scope.items = ItemService.search({
                keyword: $scope.keyword,
                categoryName: $scope.selectedCategory.categoryName,
                maxPrice: $scope.maxPrice,
                minPrice: $scope.minPrice,
                locationName: $scope.locationName
            }, function () {
                if (_.isEmpty($scope.items)) {
                	var modalInstance = $modal.open({
        			      template: '<div class="modal-header">'+
  	'<h4 class="modal-title"></h4>'
        			      +'</div><div class="modal-body"><div>No results found!</div></div>',
        			      controller: 'ModalRegistrationCtrl',
        			      size:'sm'
        			      });
                }
            });

    }
	}
        $scope.dynamicPopover = {
			    content: 'Advanced search',
			    templateUrl: 'templates/search/advancedSearch.html',
			    title: ''
			  };
        
})
.controller('SearchResultsCtrl', function ($scope,ItemService,CategoryService) {

	 $scope.currentPage = 1;
	 $scope.itemsPerPage =6;
	 $scope.maxSize = 5;
	// $scope.noOfPages = Math.ceil($scope.totalItems / $scope.itemsPerPage);
	 $scope.pageChanged = function() {

		    console.log('Page changed to: ' + $scope.currentPage);

		  };


});
