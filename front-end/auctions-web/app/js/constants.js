angular.module('auctions')
 
.constant('AUTH_EVENTS', {
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
})
 
.constant('USER_ROLES', {
  user: 'ROLE_USER',
  admin: 'ROLE_ADMIN'
})
.constant('SERVER_IP_ADDRESS', {
  ipAddress: 'localhost:8080',
  httpsIpAddress: 'localhost:8443'
});