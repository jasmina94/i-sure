/**
 * Created by desanka on 1/4/2018.
 */
(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('indexController', function ($scope, ngNotify, $state, $http, mainService) {
        var indexVm = this;
        indexVm.riskTypes = [];
        
        init();

        function init() {
        	mainService.getRiskTypes().then(
                function (response) {
                    if (response.status == 200) {
                    	indexVm.riskTypes = response.data;
                    }
                });
        }
        
        indexVm.email = "http://localhost:80/squirrelmail/src/login.php";
        
        indexVm.addRisk = false;
        indexVm.addPricelist = false;
        indexVm.addRule = false;
        
        indexVm.showRiskForm = function(){
        	indexVm.addRisk = true;
        	indexVm.addPricelist = false;
        	indexVm.addRule = false;
        }
        
        indexVm.showPricelistForm = function(){
        	indexVm.addPricelist = true;
        	indexVm.addRisk = false;
        	indexVm.addRule = false;
        }
        
        indexVm.showRuleForm = function(){
        	indexVm.addRule = true;
        	indexVm.addPricelist = false;
        	indexVm.addRisk = false;
        }
        
        indexVm.risk = {
        	      riskName: '',
        	      riskType: ''
        	    };
        
        
        indexVm.submitAddingRisk = function(){
        	mainService.createRisk(indexVm.risk).then(
                function (response) {
                    if (response.status == 200) {
                    	console.log(response.data);
                    	ngNotify.set('Successfully added risk.' , {
                            type : 'success'
                        });
                	}
            });
        }
        
       

    });

})();