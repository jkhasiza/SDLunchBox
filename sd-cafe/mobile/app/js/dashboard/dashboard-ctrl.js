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
  	      var g1, g2, g3, g4;

      window.onload = function(){
        var g1 = new JustGage({
          id: "g1",
          value: getRandomInt(0, 100),
          min: 0,
          max: 100,
          title: "Big Fella",
          label: "pounds"
        });

        var g2 = new JustGage({
          id: "g2",
          value: getRandomInt(0, 100),
          min: 0,
          max: 100,
          title: "Small Buddy",
          label: "oz"
        });

        var g3 = new JustGage({
          id: "g3",
          value: getRandomInt(0, 100),
          min: 0,
          max: 100,
          title: "Tiny Lad",
          label: "oz"
        });

        var g4 = new JustGage({
          id: "g4",
          value: getRandomInt(0, 100),
          min: 0,
          max: 100,
          title: "Little Pal",
          label: "oz"
        });

        setInterval(function() {
          g1.refresh(getRandomInt(50, 100));
          g2.refresh(getRandomInt(50, 100));
          g3.refresh(getRandomInt(0, 50));
          g4.refresh(getRandomInt(0, 50));
        }, 2500);
      };
  }
] );
