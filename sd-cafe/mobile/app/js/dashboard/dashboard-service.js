angular.module( 'app' ).factory( 'DashboardService', [ '$http', 'Constants',
  function( $http, Constants ) {
   return {
      cafeStatus: function( ) {
        return $http.get( Constants.APP_URL + 'cafe' );
      }
    };
    
  }
] );
