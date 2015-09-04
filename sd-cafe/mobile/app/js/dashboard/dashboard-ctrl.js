angular.module( 'dashboard', [] ).config( [ '$stateProvider', '$urlRouterProvider',
  function( $stateProvider, $urlRouterProvider ) {
    $stateProvider.state( 'dashboard', {
      url: '/dashboard',
      templateUrl: 'dashboard/dashboard.tpl',
      controller: 'DashboardCtrl'
    } );
  }
] ).controller( 'DashboardCtrl', [ '$scope', '$state', 'Constants', 'DashboardService',
  function( $scope,  $state, Constants, DashboardService ) {
    DashboardService.cafeStatus().success( function( response ) {
      console.log(">>>>> ",response);
      $scope.cafeStatus = response.data.numberOfUser;
      $scope.maxSeats = response.data.availableSeats;
    }).error(function( response ) {
      console.log("e >>>>> ",response);
    });

    console.log("status -- ",$scope.cafeStatus);
      var g1;
        var g1 = new JustGage({
          id: "g1",
          value: $scope.cafeStatus,
          min: 0,
          max: $scope.maxSeats,
          title: "Visitors",
          label: "ARE THERE"
        });
  }
] );
