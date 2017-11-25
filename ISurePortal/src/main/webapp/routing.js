(function() {
    'use strict';
    angular.module('iSure')
    .config(['$routeProvider', "$stateProvider", "$urlRouterProvider",
        function ($routeProvider, $stateProvider, $urlRouterProvider) {
    	 $stateProvider
         .state('homePage', {
             templateUrl: 'components/homePage.html',
             //controller: 'indexController',
             //controllerAs: 'indexControllerVm'
         });
    	 
    	
    }]);
}());