/**
 * Created by Jasmina on 15/01/2018.
 */
'use strict';

var app = angular.module('app', ['ui.router', 'ngMessages', 'ngMaterial', 'ngRoute'])
    .config(function ($routeProvider, $locationProvider, $mdThemingProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('blue-grey')
            .accentPalette('blue');

        $routeProvider.when('/', {
            templateUrl: 'template/home.html',
            controller: 'HomeController'
        });
        $routeProvider.when('/acquirer/order/:paymentId', {
            templateUrl: 'template/cardPayment.html',
            controller: 'CardPaymentController'
        });
    })