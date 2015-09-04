angular.module( 'dashboard', [] ).config( [ '$stateProvider', '$urlRouterProvider',
  function( $stateProvider, $urlRouterProvider ) {
    $stateProvider.state( 'dashboard', {
      url: '/dashboard',
      templateUrl: 'dashboard/dashboard.tpl',
      controller: 'DashboardCtrl'
    } );
  }
] ).controller( 'DashboardCtrl', [ '$scope', 'DashboardService', '$state', 'Constants',
  function( $scope, DashboardService, $state, Constants ) {

  }
] );
