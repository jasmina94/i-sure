/**
 * Created by desanka on 1/4/2018.
 */
(function() {
    'use strict';
    angular.module('iSure').factory('mainService', function mainService($http) {

    	mainService.getRiskTypes = function() {
            return $http.get("riskTypes");
            }
        
    	mainService.createRisk = function(riskDTO) {
            return $http.post("risks",riskDTO);
            }

        return mainService;

    });
})();