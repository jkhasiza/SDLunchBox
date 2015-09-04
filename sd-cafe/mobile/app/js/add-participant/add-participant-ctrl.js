angular.module( 'addParticipant', [] ).config( [ '$stateProvider', '$urlRouterProvider',
  function( $stateProvider, $urlRouterProvider ) {
    $stateProvider.state( 'addParticipant', {
      url: '/add-participant',
      templateUrl: 'add-participant/add-participant.tpl',
      controller: 'AddParticipantCtrl'
    } );
  }
] ).controller( 'AddParticipantCtrl', [ '$scope', 'AddParticipantService', '$state', 'Constants',
  function( $scope, AddParticipantService, $state, Constants ) {

  }
] );
