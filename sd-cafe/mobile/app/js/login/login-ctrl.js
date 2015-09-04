angular.module( 'login', [  ] ).config( [ '$stateProvider', '$urlRouterProvider', '$locationProvider',
  function( $stateProvider, $urlRouterProvider, $locationProvider ) {
    $locationProvider.hashPrefix( '!' );
    $stateProvider.state( 'login', {
      url: '/login',
      templateUrl: 'login/login.tpl',
      controller: 'LoginCtrl'
    } );
  }
] ).controller( 'LoginCtrl', [ '$scope', 'LoginService', '$state', 'localStorageService', 'Constants',
  function( $scope, LoginService, $state, localStorageService, Constants ) {
    $scope.user = {};
    $scope.userLogin = function( isValid ) {
      if ( isValid ) {
        LoginService.userLogin( $scope.patient ).success( function( response ) {
          localStorageService.set( Constants.AUTH_TOKEN_KEY, response.data.accessToken );
          localStorageService.set( 'sdCafe', response.data );
          $state.go( 'home' );
        } ).error( function( response ) {} );
      }
    };
    // Charts
    $scope.backend = 420;
    $scope.value1 = 42;
    $scope.value2 = 50;
    setInterval( function() {
      $scope.$apply( function() {
        $scope.value1 = getRandomInt( 10, 90 );
        $scope.value2 = getRandomInt( 10, 90 );
      } );
    }, 1000 );
  }
] );
