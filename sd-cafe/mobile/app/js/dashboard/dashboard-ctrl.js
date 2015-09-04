angular.module( 'dashboard', [] ).config( [ '$stateProvider', '$urlRouterProvider',
  function( $stateProvider, $urlRouterProvider ) {
    $stateProvider.state( 'dashboard', {
      url: '/dashboard',
      templateUrl: 'dashboard/dashboard.tpl',
      controller: 'DashboardCtrl'
    } );
  }
] ).controller( 'DashboardCtrl', [ '$scope', '$state', 'Constants',
  function( $scope,  $state, Constants ) {
      var g1;
        var g1 = new JustGage({
          id: "g1",
          value: 400,
          min: 0,
          max: 450,
          title: "Visitors",
          label: "ARE THERE"
        });
  }
] );
