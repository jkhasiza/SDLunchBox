angular.module( 'header', [] ).controller( 'HeaderCtrl', [ '$scope', '$state', '$rootScope',
  function( $scope, $state, $rootScope ) {
    $scope.logout = function() {
      $rootScope.$broadcast( 'SIGN_OUT' );
    };

    $scope.myprofile = function() {
   	$rootScope.$broadcast( 'EDIT_PROFILE' );
    };
  }
] );
