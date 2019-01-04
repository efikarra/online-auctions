angular.module('auctions.message').controller(
		'MessagesCtrl',
		function($scope, $stateParams, UserService, _, $modal,MessageService) {
			$scope.typeOfMessages=$stateParams.typeOfMessages;
			$scope.messagesToDelete =[]; 
			$scope.messages = [];
			$scope.msg={};
			console.log($scope.messages);
			if ($stateParams.typeOfMessages == 'inbox') {
				$scope.messages = UserService.receivedMessages({}, {
					userId : $scope.userId
				}, function() {

					console.log($scope.messages);
					$scope.messages = _.sortBy($scope.messages, "sendDate")
					.reverse();
				});

			} else if ($stateParams.typeOfMessages == 'sent') {
				$scope.messages = UserService.sentMessages({
					userId : $scope.userId
				});
			}
			$scope.compareFn = function (obj1, obj2) {
				return obj1.messageId === obj2.messageId;
			};	
			$scope.deleteMessages = function() {
				console.log($stateParams.typeOfMessages);
				if($stateParams.typeOfMessages=='inbox'){

				for(var i=0;i<$scope.messagesToDelete.length;i++)
				{
					$scope.msg=$scope.messagesToDelete[i];
					UserService.delReceivedMessage({},{
						userId: $scope.userId,
						id1: $scope.msg.messageId
					},function(){
					});
				}
				}else if ($stateParams.typeOfMessages == 'sent'){
					
					for(var i=0;i<$scope.messagesToDelete.length;i++)
					{
						$scope.msg=$scope.messagesToDelete[i];
						UserService.delSentMessage({},{
							userId: $scope.userId,
							id1: $scope.msg.messageId
						},function(){
						});
					}
				}
				for(var i=0;i<$scope.messagesToDelete.length;i++)
				{
				$scope.messages=_.reject($scope.messages, function (obj) {
					return obj.messageId == $scope.messagesToDelete[i].messageId;
				});
				
				}
				$scope.messagesToDelete =[]; 
			};
			$scope.messageDetailsModal = function(messageId) {
				$scope.messageId=messageId;
				var modalInstance = $modal.open({
					templateUrl : 'templates/message/messageDetails.html',
					controller : 'MessageDetailsCtrl',
					scope:$scope,
					size : 'lg'
				});
			};
		}).controller('MessagesMenuCtrl',
				function($scope, $modal,$state,$stateParams) {
			$scope.newMessageModal = function() {
				var modalInstance2 = $modal.open({
					templateUrl : 'templates/message/newMessageModal.html',
					controller : 'ModalNewMessageCtrl',
					scope:$scope,
					size : 'lg'
				});
				modalInstance2.result.then(function () {
					console.log( $state);
					$state.transitionTo($state.current, $stateParams, { reload: true, inherit: false, notify: true });
				});
			};
		})
		.controller('ModalNewMessageCtrl',
		function($scope,$state, $modalInstance,$window,UserService,MessageService) {
	console.log($scope.userId);

	$scope.newMessage = {};
	$scope.newMessage.sender = {};
	$scope.newMessage.receiver = {};
	$scope.receivers=[];
	$scope.sendMessage = function() {
		$scope.newMessage.sender.userId = $scope.userId;
		$scope.newMessage.sendDate = new Date().getTime();
		$scope.receivers = UserService.list({
			username : $scope.receiver
		}, {}, function() {
			if(!_.isEmpty($scope.receivers))
			{
				$scope.newMessage.receiver.userId=$scope.receivers[0].userId;
				MessageService.save($scope.newMessage, function() {
					
					$modalInstance.close();
					
				});
			}
			else{
				$window.alert("There is no user with the given username.");
			}
		});

	};

}).controller('MessageDetailsCtrl', function($scope,MessageService) {
console.log("ff");
			$scope.message= MessageService.get({},
					{messageId: $scope.messageId});
		});