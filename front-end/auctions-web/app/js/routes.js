angular.module('auctions')

.config(function($stateProvider, $urlRouterProvider, USER_ROLES) {
	$stateProvider.state('main', {
		url : '/auctions-web',
		abstract : true,
		views : {
			'header' : {
				templateUrl : 'templates/header.html',
				controller : 'HeaderCtrl'
			}
		}
	}).state('main.login', {
		url : '/login',
		views : {
			'container@' : {
				templateUrl : 'templates/login.html',
				controller : 'LoginCtrl'
			}
		}
	}).state('main.home', {
		url : '/home',
		views : {
			'container@' : {
				templateUrl : 'templates/home.html',
				controller : 'HomeCtrl'
			}

		}
	}).state('main.home.search', {
		url : '/search',
		views : {
			'search' : {
				templateUrl : 'templates/search/search.html',
				controller : 'SearchCtrl'
			},
			'search-results@main.home.search' : {
				templateUrl : 'templates/search/searchResults.html',
				controller : 'SearchResultsCtrl'
			}
		}
	}).state('main.register', {
		url : '/register',
		views : {
			'container@' : {
				templateUrl : 'templates/register.html',
				controller : 'RegisterCtrl'
			}
		}
	}).state('main.itemId', {
		url : '/items/item/:itemId',
		views : {
			'container@' : {
				templateUrl : 'templates/item.html',
				controller : 'ItemDetailsCtrl',
				cache : false
			}
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.newItem', {
		url : '/newItem',
		views : {
			'container@' : {
				templateUrl : 'templates/newItem.html',
				controller : 'NewItemCtrl',
				cache : false
			}
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.admin', {
		url : '/admin',
		views : {
			'container@' : {
				templateUrl : 'templates/admin.html',
			}
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin ]
		}
	}).state('main.admin.users', {
		url : '/users',
		views : {
			'users' : {
				templateUrl : 'templates/users.html',
				controller : 'UsersListCtrl'
			}
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin ]
		}
	}).state('main.admin.users.userId', {
		url : '/:userId',
		views : {
			'user-details' : {
				templateUrl : 'templates/userDetails.html',
				controller : 'UserDetailsCtrl'
			}

		},
	data : {
		authorizedRoles : [ USER_ROLES.admin ]
	}
	}).state('main.messagesMenu', {
		url : '/messages',
		views : {
			'container@' : {
				templateUrl : 'templates/sideMenu.html',
			},
			'side-menu@main.messagesMenu' : {
				templateUrl : 'templates/message/messagesMenu.html',
				controller : 'MessagesMenuCtrl'
			}

		},
	data : {
		authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
	}
	}).state('main.messagesMenu.messages', {
		url : '/:typeOfMessages',
		views : {
			'menu-details' : {
				templateUrl : 'templates/message/messages.html',
				controller : 'MessagesCtrl'
			}
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu', {
		url : '/user-account',
		views : {
			'container@' : {
				templateUrl : 'templates/sideMenu.html',
			},
			'side-menu@main.accountMenu' : {
				templateUrl : 'templates/user-account/accountMenu.html',
				controller : 'AccountMenuCtrl'
			}

		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions', {
		url : '/items',
		abstract : true
	}).state('main.accountMenu.auctions.inActive', {
		url : '/inActive',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/user-auctions.html',
				controller : 'UserAuctionsCtrl'
			}

		},
		params : {
			typeOfAuction : 'inActive'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions.inActive.itemId', {
		url : '/:itemId',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/auction-edit.html',
				controller : 'AuctionEditCtrl'
			}
		},
		params : {
			typeOfAuction : 'inActive'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions.active', {
		url : '/active',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/user-auctions.html',
				controller : 'UserAuctionsCtrl'
			}
		},
		params : {
			typeOfAuction : 'active'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions.active.itemId', {
		url : '/:itemId',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/auction-details.html',
				controller : 'AuctionDetailsCtrl'
			}
		},
		params : {
			typeOfAuction : 'active'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions.ended', {
		url : '/ended',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/user-auctions.html',
				controller : 'UserAuctionsCtrl'
			}
		},
		params : {
			typeOfAuction : 'ended'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.auctions.ended.itemId', {
		url : '/:itemId',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/auction-details.html',
				controller : 'AuctionDetailsCtrl'
			}
		},
		params : {
			typeOfAuction : 'ended'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	}).state('main.accountMenu.bids', {
		url : '/bids',
		abstract : true
	})
	.state('main.accountMenu.bids.active', {
		url : '/active',
		views : {
			'menu-details@main.accountMenu' : {
				templateUrl : 'templates/user-account/user-bids.html',
				controller : 'UserBidsCtrl'
			}
		},
		params : {
			typeOfBid : 'active'
		},
		data : {
			authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
		}
	})
		.state('main.accountMenu.bids.inActive', {
			url : '/ended',
			views : {
				'menu-details@main.accountMenu' : {
					templateUrl : 'templates/user-account/user-bids.html',
					controller : 'UserBidsCtrl'
				}
			},
			params : {
				typeOfBid : 'inActive'
			},
			data : {
				authorizedRoles : [ USER_ROLES.admin, USER_ROLES.user ]
			}
	})
	$urlRouterProvider.otherwise(function($injector, $location) {
		var $state = $injector.get("$state");
		$state.go("main.home.search");
	});

});