angular.module( 'app' ).factory( 'PatientVerificationService', [ '$http', 'Constants',
  function( $http, Constants ) {
    return {
      loginUser: function( doctor ) {
        return $http.post( Constants.APP_URL + 'doctor/login', doctor );
      },
      registerUser: function( doctor ) {
        return $http.post( Constants.APP_URL + 'doctor/register', doctor );
      },
      validateToken: function( token ) {
        return $http.get( Constants.APP_URL + 'user/validate-token/' + token );
      },
      activateAccount: function( patient ) {
        return $http.put( Constants.APP_URL + 'patient/activate-account', patient );
      },
      logout: function() {
        return $http.post( Constants.APP_URL + 'user/logout' );
      },
      sendSmsCode : function(mobileNumber) {
        return $http.post( Constants.APP_URL + 'patient/send-sms-code', mobileNumber );
      }
    };
  }
] );
