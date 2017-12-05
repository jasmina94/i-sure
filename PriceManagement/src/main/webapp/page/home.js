/**
 * Created by Jasmina on 16/11/2017.
 */
app.controller('HomeController', function ($scope, $state, $location, $log, $rootScope, $mdSidenav, $mdDialog, $interval, authenticationService) {

    $scope.page.current = 0;

    $scope.authService = authenticationService;
});