(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('insuranceStepperController', function ($scope, $q, $timeout, insuranceService) {

        var vm = this;

        init();

        function init() {
            vm.selectedStep = 0;
            vm.maxStep = 6;
            vm.showBusyText = false;
            vm.stepTwo = {
                notCompleted: false, optional: false,
                data: {}
            };
            insuranceService.getTravelInsuranceRisks("International Travel").then(
                function (response) {
                    if (response.status == 200) {
                        vm.travelRisks = response.data;
                        vm.stepOne = {
                            notCompleted: true, optional: false, data: {
                                selectedRegion: vm.travelRisks['Region'][0],
                                numberOfPeople: {},
                                selectedSport: vm.travelRisks['Sport'][0],
                                selectedAmount: vm.travelRisks['Value'][0]
                            }
                        };
                    }
                })
        }

        vm.enableNextStep = function nextStep() {
            //do not exceed into max step
            if (vm.selectedStep >= vm.maxStep) {
                return;
            }
            //do not increment vm.stepProgress when submitting from previously completed step
            if (vm.selectedStep === vm.stepProgress - 1) {
                vm.stepProgress = vm.stepProgress + 1;
            }
            vm.selectedStep = vm.selectedStep + 1;
        };

        vm.moveToPreviousStep = function moveToPreviousStep() {
            if (vm.selectedStep > 0) {
                vm.selectedStep = vm.selectedStep - 1;
            }
        };

        vm.calculateDays = function calculateDays(fromDate, toDate) {
            var timeDiff = Math.abs(fromDate.getTime() - toDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            return diffDays;
        };
        vm.showHome = true;
        vm.submitCurrentStep = function submitCurrentStep(what) {
            if (what !== 'none') {
                if (what === 'home') {
                    vm.showHome = false;
                    if (vm.homeRisks === undefined) {
                        insuranceService.getTravelInsuranceRisks("Home").then(
                            function (response) {
                                if (response.status == 200) {
                                    console.log(response.data);
                                    vm.homeRisks = response.data;
                                    vm.stepThree = {
                                        notCompleted: false, optional: true,
                                        data: {
                                            selectedArea: vm.homeRisks['Surface area'][0],
                                            selectedAge: vm.homeRisks['Property age'][0],
                                            selectedValue: vm.homeRisks['Estimated value of property'][0],
                                            selectedRisk: vm.homeRisks['Property risks'][0]
                                        }
                                    };
                                }
                            })
                    }
                } else if (what === 'roadside') {
                	if (vm.carRisks === undefined) {
                        insuranceService.getTravelInsuranceRisks("Roadside").then(
                            function (response) {
                                if (response.status == 200) {
                                    console.log(response.data);
                                    vm.carRisks = response.data;
                                    vm.stepFour = {
                                        notCompleted: false, optional: true,
                                        data: {
                                        	selectedAccommodation: vm.carRisks['Accommodation'][0],
                                        	selectedRepair: vm.carRisks['Repair'][0],
                                        	selectedTowing: vm.carRisks['Towing'][0],
                                        	selectedTransport: vm.carRisks['Transport'][0]
                                        }
                                    };
                                }
                            })
                    }
                }
            }
            //validation goes here , if its satisfied then it's this
            vm.stepOne.notCompleted = false;
         	vm.addTabs();
            vm.selectedStep = vm.selectedStep + 1;
        };

        vm.checkIfEnabled = function(){
            return vm.stepOne.notCompleted;
        };

        vm.todayDate = new Date();

        vm.isDateValid = function (date) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            var d = new Date(date);
            d.setHours(0, 0, 0, 0);
            return d >= today;
        };

        vm.pay = 'Pay1';
        
        vm.addTabs = function(){
        	vm.tabs=[];
        	var totalPeople = sumNumberOfPeople();
        	for(var i=1;i<=totalPeople;i++){
        		vm.tabs.push(""+i);
        	}
        }
        
        function sumNumberOfPeople(){
        	var people = vm.stepOne.data.numberOfPeople;
        	var totalPeople = 0;
        	var value = 0;
        	for(var num in people) {
        		  value = people[num];
        		  totalPeople += value; 
        	}
        	return totalPeople;
        }
        
        function createPatternOfDate(date){
        	var pattern = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        	return pattern;
        }
        
        
        vm.createInsurancePolicy = function(){
        	
        	var customers = [];
        	var customer = {};
        	var customerData =vm.stepTwo.data; 
        	for(var c in customerData)
        	{	
        		if(customerData[c] != null){
	        		if(!customerData[c].carrier){
	        			customerData[c].email = null;
	        		}
	        		customer = {
	        				"firstName": customerData[c].firstname,	
	        				"lastName": customerData[c].lastname,
	        				"personalId": customerData[c].personalId,
	        				"passport": customerData[c].passport,
	        				"address": customerData[c].address,
	        				"telephoneNumber": customerData[c].telephoneNumber,
	        				"carrier": customerData[c].carrier,
	        				"email": customerData[c].email
	        		}	
	        		customers.push(customer);
	        	}
        	}
        	console.log(customers);
        	
        	var travelRisks = [];
        	travelRisks.push(vm.stepOne.data.selectedRegion);
        	travelRisks.push(vm.stepOne.data.selectedSport);
        	travelRisks.push(vm.stepOne.data.selectedAmount);
        	
        	var internationalTravelInsuranceDTO = 
        	{
        		"issueDate": createPatternOfDate(vm.stepOne.data.fromDate),
        		"durationInDays": vm.calculateDays(vm.stepOne.data.fromDate, vm.stepOne.data.toDate),
        		"numberOfPersons": sumNumberOfPeople(),
        		"price": 12000,
        		"risks": travelRisks
        		//"insurancePolicies": null

        	}
        	console.log(internationalTravelInsuranceDTO);
        	
        	var homeRisks = [];
        	homeRisks.push(vm.stepThree.data.selectedArea);
        	homeRisks.push(vm.stepThree.data.selectedAge);
        	homeRisks.push(vm.stepThree.data.selectedRisk);
        	homeRisks.push(vm.stepThree.data.selectedValue);
        	
        	var homeInsuranceDTO = 
        	{
        		"ownerFirstName": vm.stepThree.data.firstname,
        		"ownerLastName": vm.stepThree.data.lastname,
        		"address": vm.stepThree.data.address,
        		"personalId": vm.stepThree.data.personalId,
        		"price": 10000,
        		"risks": homeRisks
        	}
        	console.log(homeInsuranceDTO);
        	
        	var roadsideRisks = [];
        	roadsideRisks.push(vm.stepFour.data.selectedAccommodation);
        	roadsideRisks.push(vm.stepFour.data.selectedRepair);
        	roadsideRisks.push(vm.stepFour.data.selectedTowing);
        	roadsideRisks.push(vm.stepFour.data.selectedTransport);

        	var roadsideAssistanceInsuranceDTO = 
        	{
        		"ownerFirstName": vm.stepFour.data.ownerFirstname,
        		"ownerLastName": vm.stepFour.data.ownerLastname,
        		"personalId": vm.stepFour.data.personalId,
        		"carBrand": vm.stepFour.data.carBrand,
        		"carType": vm.stepFour.data.carType,
        		"yearOfManufacture": vm.stepFour.data.yearOfManufacture,
        		"licencePlateNumber": vm.stepFour.data.licencePlateNumber,
        		"undercarriageNumber": vm.stepFour.data.undercarriageNumber,
        		"price": 20000,
        		"risks": roadsideRisks
        	}
        	console.log(roadsideAssistanceInsuranceDTO);

        	var insurancePolicyDTO = 
        	{
        		"totalValue": 50000,
        		"dateOfIssue": "2017-12-10",
        		"dateBecomeEffective": "2017-12-15",
        		"customers": customers,
        		"internationalTravelInsurance": internationalTravelInsuranceDTO,
        		"homeInsurance": homeInsuranceDTO,
        		"roadsideAssistanceInsurance": roadsideAssistanceInsuranceDTO
        	}
        	 insuranceService.createInsurancePolicy(insurancePolicyDTO).then(
                 function (response) {
                     if (response.status == 200) {
                         console.log(response.data);
                     }
                 })
		    }
	});
}());