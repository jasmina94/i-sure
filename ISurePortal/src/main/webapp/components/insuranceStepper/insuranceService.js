/**
 * Created by milca on 12/5/2017.
 */
(function() {
    'use strict';
    angular.module('iSure').factory('insuranceService', function insuranceService($http) {

    insuranceService.getTravelInsuranceRisks = function (insuranceType) {
        return $http.get("/riskTypes/insuranceCategory/" + insuranceType);
    }

    insuranceService.createInsurancePolicy = function (insurancePolicyDTO) {
        return $http.post("/insurancePolicies", insurancePolicyDTO);
    }
    
    insuranceService.createInquiry = function (transactionDTO) {
        return $http.post("/inquiries", transactionDTO);
    }

    return insuranceService;

    });
})();