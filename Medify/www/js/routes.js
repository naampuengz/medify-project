angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    
  .state('page5', {
    url: '/login_page',
    templateUrl: 'templates/login.html',
    controller: 'page5Ctrl'
  })

  .state('page6', {
    url: '/appoitment_summary_page',
    templateUrl: 'templates/appoitment_summary_page.html',
    controller: 'page6Ctrl'
  })

  .state('page7', {
    url: '/manage_medicine_page',
    templateUrl: 'templates/manage_medicine_page.html',
    controller: 'page7Ctrl'
  })

  .state('page8', {
    url: '/reister_page',
    templateUrl: 'templates/reister_page.html',
    controller: 'page8Ctrl'
  })

  .state('page10', {
    url: '/list_medicine_page',
    templateUrl: 'templates/list_medicine_page.html',
    controller: 'page10Ctrl'
  })

  .state('page9', {
    url: '/add_appointment_page',
    templateUrl: 'templates/add_appointment_page.html',
    controller: 'page9Ctrl'
  })

  .state('page11', {
    url: '/history_page',
    templateUrl: 'templates/history_page.html',
    controller: 'page11Ctrl'
  })

  .state('page12', {
    url: '/connect_device_page',
    templateUrl: 'templates/connect_device_page.html',
    controller: 'page12Ctrl'
  })

  .state('page13', {
    url: '/setting_page',
    templateUrl: 'templates/setting_page.html',
    controller: 'page13Ctrl'
  })

  .state('page15', {
    url: '/first_page',
    templateUrl: 'templates/first_page.html',
    controller: 'page15Ctrl'
  })

$urlRouterProvider.otherwise('/login_page')

  

});