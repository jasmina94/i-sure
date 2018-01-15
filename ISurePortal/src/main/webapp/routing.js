(function () {
    'use strict';
    angular.module('iSure')
        .config(['$routeProvider', "$stateProvider",
            function ($routeProvider, $stateProvider) {
                $stateProvider
                    .state('homePage', {
                        templateUrl: 'components/homePage.html'
                    })
                    .state('insurancePage', {
                        templateUrl: 'components/insuranceStepper/stepper.html',
                        controller: 'insuranceStepperController',
                        controllerAs: 'vm'
                    });
            }]);
}());