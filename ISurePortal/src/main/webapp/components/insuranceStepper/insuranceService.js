/**
 * Created by milca on 12/5/2017.
 */
(function() {
    'use strict';
    var url = "http://localhost:8090/insurance/";

    var urlTransactions = "http://localhost:8090/transactions/"
   // var urlInquiries = "http://localhost:9030/inquiries" 
    	
//    var urlTransactions = "http://localhost:9030/transactions/"
    var urlInquiries = "http://localhost:9030/inquiries/"

    angular.module('iSure').factory('insuranceService', function insuranceService($http) {

        insuranceService.getTravelInsuranceRisks = function(insuranceType) {
            return $http.get(url + "riskTypes/insuranceCategory/" + insuranceType);
            }
        
        insuranceService.createInsurancePolicy = function(insurancePolicyDTO) {
            return $http.post(url + "insurancePolicies",insurancePolicyDTO);
            }
        
        insuranceService.createTransaction = function(transactionDTO) {
           return $http.post(urlTransactions + "transactions",transactionDTO);
        	//return $http.get(urlTransactions + "transactions");
            }
        
        insuranceService.createInquiry = function(transactionDTO) {

            return $http.post(urlTransactions + 'inquiries',transactionDTO);
         	//return $http.get(urlTransactions + "transactions");
             }
        
        return insuranceService;

    });
})();