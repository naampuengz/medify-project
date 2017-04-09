angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    
  

      .state('tabsController', {
    url: '/page1',
    templateUrl: 'templates/tabsController.html',
    abstract:true
  })

  .state('page5', {
    url: '/login_page',
    templateUrl: 'templates/page5.html',
    controller: 'page5Ctrl'
  })

  .state('page6', {
    url: '/appoitment_summary_page',
    templateUrl: 'templates/page6.html',
    controller: 'page6Ctrl'
  })

  .state('page7', {
    url: '/manage_medicine_page',
    templateUrl: 'templates/page7.html',
    controller: 'page7Ctrl'
  })

  .state('page8', {
    url: '/reister_page',
    templateUrl: 'templates/page8.html',
    controller: 'page8Ctrl'
  })

  .state('page10', {
    url: '/list_medicine_page',
    templateUrl: 'templates/page10.html',
    controller: 'page10Ctrl'
  })

  .state('page9', {
    url: '/add_appointment_page',
    templateUrl: 'templates/page9.html',
    controller: 'page9Ctrl'
  })

  .state('page11', {
    url: '/history_page',
    templateUrl: 'templates/page11.html',
    controller: 'page11Ctrl'
  })

  .state('page12', {
    url: '/connect_device_page',
    templateUrl: 'templates/page12.html',
    controller: 'page12Ctrl'
  })

  .state('page13', {
    url: '/setting_page',
    templateUrl: 'templates/page13.html',
    controller: 'page13Ctrl'
  })

  .state('page15', {
    url: '/first_page',
    templateUrl: 'templates/page15.html',
    controller: 'page15Ctrl'
  })

$urlRouterProvider.otherwise('/login_page')

  

});