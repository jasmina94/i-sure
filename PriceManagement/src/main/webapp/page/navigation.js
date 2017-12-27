/**
 * Created by Jasmina on 16/11/2017.
 */
app.controller('NavigationController', function ($scope, $state, $location, $log, $rootScope, $mdSidenav, $mdDialog, authenticationService) {

    $scope.page = {
        title: 'Magacin',
        current: -1
    };

    $scope.user = authenticationService.getUser();
    $scope.authService = authenticationService;

    $scope.logout = function () {
        authenticationService.logout(function () {
            $state.transitionTo('login');
        }, function () {

        });
    };
});