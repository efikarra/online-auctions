angular.module('auctions.item') 
.controller('ItemDetailsCtrl', function ($scope, $stateParams, ItemService, _,$state,$window) {
	$scope.windowOptions = {
    	    show: false
    	};
        $scope.item = {};
        $scope.item.ends; 
        $scope.item.bids = [];
        console.log( $scope.item.bids);
        $scope.auctionEnded=false;
        console.log( $scope.item.ends);
        $scope.newBid={};
        $scope.newBid.bidder={};
        $scope.newBid.item={};
        $scope.bidAllowed=false;
        $scope.position={};
        $scope.center={};
        $scope.map = {};
        $scope.marker = {};
        var now=new Date();

        
        //retrieve item details
        $scope.item = ItemService.get({}, {
            id1: $stateParams.itemId
        }, function () {
            $scope.item.bids =ItemService.bids({
                id1: $scope.item.itemId
            },function(){
    	
            });
            console.log( $scope.item);
            console.log( $scope.item.ends);
            if(now.getTime()>$scope.item.ends)
            	{
            	$scope.auctionEnded=true;
            	}
            else
            	{
            	$scope.auctionEnded=false;
            	}
           
            $scope.position.latitude=parseFloat($scope.item.location.latitude);
            $scope.position.longitude= parseFloat($scope.item.location.longitude);
            $scope.center.latitude=parseFloat($scope.item.location.latitude);
            $scope.center.longitude= parseFloat($scope.item.location.longitude);
            $scope.map = { center: $scope.center, zoom: 7 };

            $scope.marker = {
            		  coords: $scope.position,
            		 id: 1,
            		 markersEvents: {
            		        click: function(marker, eventName, model, args) {
            		          $scope.windowOptions.show = !$scope.windowOptions.show;  
            		          console.log($scope.windowOptions.show);
            		          
            		        }
            		 }
            		};
            $scope.closeClick = function() {
                $scope.windowOptions.show = !$scope.windowOptions.show;
                console.log($scope.windowOptions.show);
            };
            });
        $scope.$on('timer-stopped', function (event, data){
        	$scope.auctionEnded=true;
        });
        
        $scope.saveBid = function () {
            $scope.newBid.bidder.userId = $scope.userId;
            $scope.newBid.item.itemId = $scope.item.itemId;
            $scope.newBid.time=new Date().getTime();
            console.log($scope.newBid);
            if($window.confirm('Are you sure you want to submit this bid?')) {
            	ItemService.saveBid({
                    id1: $scope.item.itemId
                }, $scope.newBid, function () {
                	$state.transitionTo($state.current, $stateParams, { reload: true, inherit: false, notify: true });
                });
              }
            else
            	{
            	
            	}
            
        };

        //config of carousel for photos' display
        $scope.myInterval = 5000;
        $scope.noWrapSlides = false;
        
        //map configuration    
       
    })
    ;