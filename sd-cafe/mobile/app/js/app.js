angular.module( 'app', [ 'ui.router', 'ui.bootstrap', 'header', 'home',  'LocalStorageModule',
  'login', 'dashboard', 'addParticipant'
] ).config( function( $httpProvider, localStorageServiceProvider, $locationProvider ) {
  $httpProvider.interceptors.push( 'SessionInjector' );
  $httpProvider.interceptors.push( 'ResponseInterceptor' );
  localStorageServiceProvider.prefix = 'sdCafe';

} ).run( [ 'localStorageService', 'PatientVerificationService', 'Constants', '$state',
  function( localStorageService, PatientVerificationService, Constants, $state ) {}
] );
