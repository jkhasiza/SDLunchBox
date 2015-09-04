angular.module( 'app', [ 'ui.router', 'ui.bootstrap', 'header', 'home', 'patient-verification', 'LocalStorageModule',
  'login'
] ).config( function( $httpProvider, localStorageServiceProvider, $locationProvider ) {
  $httpProvider.interceptors.push( 'SessionInjector' );
  $httpProvider.interceptors.push( 'ResponseInterceptor' );
  localStorageServiceProvider.prefix = 'sdCafe';
} ).run( [ 'localStorageService', 'PatientVerificationService', 'Constants', '$state',
  function( localStorageService, PatientVerificationService, Constants, $state ) {}
] );
