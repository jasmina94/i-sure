(function() {
    'use strict';
    angular.module('iSure')
    .config(['$routeProvider', "$stateProvider", "$urlRouterProvider",
        function ($routeProvider, $stateProvider, $urlRouterProvider) {
    	 $stateProvider
         .state('homePage', {
             templateUrl: 'components/insuranceStepper/stepper.html',
             controller: 'insuranceStepperController',
             controllerAs: 'vm'
         });
    	 
    	
    }]);
}());