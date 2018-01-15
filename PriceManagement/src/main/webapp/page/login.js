/**
 * Created by Jasmina on 16/11/2017.
 */
app.controller('LoginController', function ($scope, $state, $http, $mdDialog, authenticationService, userService) {

    $scope.submit = function () {
        authenticationService.login($scope.user, function () {
            $http.get('/api/users/me')
                .success(function (user) {
                    authenticationService.setUser(user);
                    if (authenticationService.isEmployee()) {
                        $state.transitionTo('navigation.home');
                    }
                })
                .error(error);
        }, error);
    };

    var error = function () {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.body))
                .title('Login failed!')
                .content('Wrong username or password.')
                .ok('Ok')
        );
    };
});