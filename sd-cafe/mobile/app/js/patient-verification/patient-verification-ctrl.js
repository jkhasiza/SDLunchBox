angular.module( 'patient-verification', [] ).config( [ '$stateProvider', '$urlRouterProvider', '$locationProvider',
  function( $stateProvider, $urlRouterProvider, $locationProvider ) {
    $locationProvider.hashPrefix( '!' );
    $stateProvider.state( 'patient-verification', {
      url: '/patient-verification/:accessToken',
      templateUrl: 'patient-verification/patient-verification.tpl',
      controller: 'PatientVerificationCtrl',
      resolve: {
        patient: [ 'PatientVerificationService', '$stateParams',
          function( PatientVerificationService, $stateParams ) {
            return PatientVerificationService.validateToken( $stateParams.accessToken );
          }
        ]
      }
    } );
  }
] ).controller( 'PatientVerificationCtrl', [ '$scope', '$state', 'patient', 'PatientVerificationService',
  'localStorageService', 'Constants', '$stateParams',
  function( $scope, $state, patient, PatientVerificationService, localStorageService, Constants, $stateParams ) {
    
      
    $scope.patient = patient.data.data;
    localStorageService.set( Constants.AUTH_TOKEN_KEY, $stateParams.accessToken );
    $scope.sendCode = function() {
      console.log( "Acccess token", $stateParams.accessToken );
      // window.location = "http://www.google.com/";
      PatientVerificationService.sendSmsCode( $scope.patient.contactInformation.mobileNumber ).success( function(
        savedPatient ) {
        toastr.success( 'Verification Code Sent ' );
      } ).error( function( error ) {
        console.log( "error ye hai", error.messages );
        toastr.error( error.messages );
      } );
    };
    $scope.activateAccount = function() {
      console.log( "BEFORE VERIFICATION", $scope.patient );
      PatientVerificationService.activateAccount( $scope.patient ).success( function( response ) {
        console.log( "AFTER VERIFICATION", response.data );
        localStorageService.set( Constants.AUTH_TOKEN_KEY, response.data.accessToken );
        localStorageService.set( 'patient', response.data );
        $state.go( 'home' );
      } );
    };
  }
] );
