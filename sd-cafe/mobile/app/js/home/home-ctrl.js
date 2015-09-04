angular.module( 'home', [] ).config( function( $stateProvider, $urlRouterProvider, $locationProvider ) {
  $locationProvider.hashPrefix( '!' );
  $urlRouterProvider.otherwise( '/home' );
  $stateProvider.state( 'home', {
    url: '/home',
    templateUrl: 'home/home.tpl',
    controller: 'HomeCtrl'
  } );
} ).controller( 'HomeCtrl', [ '$scope', 'localStorageService', 'Constants', '$state',
'$rootScope',
function($scope, localStorageService, PatientVerificationService, Constants, $state,
  $rootScope ) {

  if ( !localStorageService.get( Constants.AUTH_TOKEN_KEY ) ) {
    $state.go( 'login' );
  } else {
    PatientVerificationService.validateToken( localStorageService.get( Constants.AUTH_TOKEN_KEY ) ).success( function( resp ) {
     
    } );
  }
  $scope.user = localStorageService.get( 'sdCafe' );
  console.log( "sdCafe:", $scope.user );
  $scope.$on( 'SIGN_OUT', function() {

    PatientVerificationService.logout().success( function( response ) {
      $state.go( 'login' );
        // localStorageService.clearAll();
        localStorageService.remove( Constants.AUTH_TOKEN_KEY );
        localStorageService.remove( 'sdCafe' );
      } ).error( function( response ) {
      } );
    } );
}
] );
