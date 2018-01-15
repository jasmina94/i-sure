/**
 * Created by Jasmina on 15/01/2018.
 */
app.controller('CardPaymentController', function ($scope, $state, $routeParams, cardPaymentService){

    var months = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    var years = [2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028];

    $scope.months = months;
    $scope.years = years;
    $scope.paymentId = $routeParams.paymentId;

    var loadName = function () {
        cardPaymentService.getBankName(function(response){
            $scope.bankName = response.data.name;
        });
    }

    var loadAmount = function () {
        var id = $scope.paymentId;
        cardPaymentService.getAmountForPayment(id, function(response){
            var amount = response.data.amount;
            amount = parseFloat(amount);
            $scope.amount = amount;
        });
    }

    loadName();
    loadAmount();


    $scope.pay = function () {
        $scope.order.amount = $scope.amount;
        console.log($scope.order);
        cardPaymentService.processOrder($scope.paymentId, $scope.order, function(response){
            console.log(response);
            $scope.close();
        });
    }

    $scope.cancel = function () {
        window.location = document.referrer;
    }
});