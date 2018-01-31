/**
 * Created by milca on 12/5/2017.
 */
(function() {
    'use strict';
    angular.module('iSure').factory('insuranceService', function insuranceService($http) {

        insuranceService.getTravelInsuranceRisks = function(insuranceType) {
            return $http.get("/riskTypes/insuranceCategory/" + insuranceType);
            }
        
        insuranceService.createInsurancePolicy = function(insurancePolicyDTO) {
            return $http.post("/insurancePolicies",insurancePolicyDTO);
            }
        
        insuranceService.getPrice = function (insurancePolicyDTO) {
            return $http.post("/price", insurancePolicyDTO);
        }
        
        insuranceService.logout = function () {
            return $http.get("/logout");
        }
        return insuranceService;

    });
})();