angular.module( 'app' ).factory( 'LoginService', [ '$http', 'Constants',
  function( $http, Constants ) {
    return {
      userLogin: function( user ) {
        return $http.post( Constants.APP_URL + 'user/login', user );
      }
    };
  }
] );
