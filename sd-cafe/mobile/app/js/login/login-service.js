angular.module( 'app' ).factory( 'LoginService', [ '$http', 'Constants',
  function( $http, Constants ) {
    return {
      userLogin: function( user ) {
        return $http.get( Constants.APP_URL + 'cafe/login?mobileNumber=user.mobileNumber&deviceId=user.deviceId');
      }
    };
  }
] );
