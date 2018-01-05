/**
 * Created by desanka on 1/4/2018.
 */
(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('indexController', function ($scope, ngNotify, $state, $http, mainService) {
        var indexVm = this;
        indexVm.riskTypes = [];
        indexVm.currentlyActivePricelist = null;
        indexVm.allRisks = [];
        indexVm.residualRisks = [];
        indexVm.risk = {
      	      riskName: '',
      	      riskType: ''
      	    };
      
      indexVm.pricelistItem = {
      		coefficient : 1,
      		price: 0,
      		risk: {}
      }
      
      indexVm.pricelistItems = []
      
      
      indexVm.pricelist = {
      		dateFrom: '',
      		dateTo: '',
      		pricelistItems : indexVm.pricelistItems
      }
        
        init();

        function init() {
        	mainService.getRiskTypes().then(
                function (response) {
                    if (response.status == 200) {
                    	indexVm.riskTypes = response.data;
                    }
                });
        	
        	mainService.findCurrentlyActive().then(
        			function (response) {
                        if (response.status == 200) {
                        	indexVm.currentlyActivePricelist = response.data;
                        	if(indexVm.currentlyActivePricelist != null){
                        		indexVm.pricelistItems = indexVm.currentlyActivePricelist.pricelistItems;
                        		/*for (var i=0;i<indexVm.pricelistItems.length;i++){
                        			mainService.getPricelistById(indexVm.pricelistItems[i].id).then(
                        	                function (response) {
                        	                    if (response.status == 200) {
                        	                    	indexVm.pricelistItems[i].risk = response.data.risk;
                        	                    }
                        	                });
                        		}*/
                        		
                        	}
                        }else if(response.status == 500){
                        	indexVm.pricelistItems = [];
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
        	mainService.getRisks().then(
                function (response) {
                    if (response.status == 200) {
                    	indexVm.allRisks = response.data;
                    	if(indexVm.currentlyActivePricelist == null){
                    		indexVm.addNew();
                    	}
                    	residualRisks();
                    }
                });
        }
        
        indexVm.showRuleForm = function(){
        	indexVm.addRule = true;
        	indexVm.addPricelist = false;
        	indexVm.addRisk = false;
        }
        
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
        
        indexVm.todayDate = new Date();

        indexVm.isDateValid = function (date) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            var d = new Date(date);
            d.setHours(0, 0, 0, 0);
            return d >= today;
        };
        
        function residualRisks(){
        	var risksWithPrice = [];
        	var allRisks = indexVm.allRisks;
        	angular.forEach(indexVm.pricelistItems, function(pricelistItem) {
            	risksWithPrice.push(pricelistItem.risk);
            });
        	indexVm.residualRisks = allRisks.filter(function(item) { return risksWithPrice.indexOf(item) === -1; });
        	console.log(indexVm.residualRisks)
        }
        
        indexVm.addNew = function(){
        	indexVm.pricelistItems.push({ 
                	coefficient : '',
            		price: '',
            		risk: null
                });
        	residualRisks();
        };
        
        indexVm.remove = function(){
            var newDataList=[];
            $scope.selectedAll = false;
            angular.forEach(indexVm.pricelistItems, function(selected){
                if(!selected.selected){
                    newDataList.push(selected);
                }
            }); 
            indexVm.pricelistItems = newDataList;
            residualRisks();
        };
        
        indexVm.checkAll = function () {
            if (!$scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach(indexVm.pricelistItems, function(pricelistItem) {
            	pricelistItem.selected = $scope.selectedAll;
            });
        }; 
        
        function createPatternOfDate(date){
            var pattern = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()+" "+date.getHours()+":"+date.getMinutes();
            return pattern;
        }
        
        indexVm.submitAddingPricelist = function(){
        	indexVm.pricelist.dateTo = createPatternOfDate(indexVm.pricelist.dateTo);
        	mainService.createPricelist(indexVm.pricelist).then(
                function (response) {
                    if (response.status == 200) {
                    	console.log(response.data);
                    	ngNotify.set('Successfully added pricelist.' , {
                            type : 'success'
                        });
                	}
                    indexVm.addPricelist = false;
            });
        }

    });

})();