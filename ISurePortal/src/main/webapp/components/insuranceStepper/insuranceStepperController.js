(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('insuranceStepperController', function ($scope, insuranceService, ngNotify, $state) {

        var vm = this;

        init();

        function init() {
            vm.selectedStep = 0;
            vm.maxStep = 6;
            vm.showBusyText = false;
            vm.tabs=[];
            vm.dummy = {};

            vm.stepTwo = {
                completed: false, optional: false,
                data: {}
            };

            insuranceService.getTravelInsuranceRisks("International Travel").then(
                function (response) {
                    if (response.status == 200) {
                    	vm.travelRisks = response.data;
                        vm.stepOne = {
                            completed: false, optional: false, data: {
                                selectedRegion: vm.travelRisks['Region'][0],
                                numberOfPeople: {},
                                selectedSport: vm.travelRisks['Sport'][0],
                                selectedAmount: vm.travelRisks['Value'][0]
                            }
                        };
                        vm.tabs.push(""+1);                    }
                });

            insuranceService.getTravelInsuranceRisks("Home").then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        vm.homeRisks = response.data;
                        vm.stepThree = {
                            completed: false, optional: true, isSkiped: true,
                            data: {
                                selectedArea: vm.homeRisks['Surface area'][0],
                                selectedAge: vm.homeRisks['Property age'][0],
                                selectedValue: vm.homeRisks['Estimated value of property'][0],
                                selectedRisk: vm.homeRisks['Property risks'][0]
                            }
                        };
                    }
                });

            insuranceService.getTravelInsuranceRisks("Roadside").then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        vm.carRisks = response.data;
                        vm.stepFour = {
                            completed: false, optional: true, isSkiped: true,
                            data: {
                                selectedAccommodation: vm.carRisks['Accommodation'][0],
                                selectedRepair: vm.carRisks['Repair'][0],
                                selectedTowing: vm.carRisks['Towing'][0],
                                selectedTransport: vm.carRisks['Transport'][0]
                            }
                        };
                    }
                });
        }

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

        vm.submitCurrentStep = function submitCurrentStep() {
            switch (vm.selectedStep) {
                case 0: {vm.addTabs();} break;
                case 2: vm.stepThree.isSkiped = false; break;
                case 3: vm.stepFour.isSkiped = false; break;
            }
            vm.selectedStep = vm.selectedStep + 1;
        };

        vm.cancel = function cancel() {
            $state.go('homePage');
        }

        vm.skip = function skip(whatIsSkipped) {
            if(whatIsSkipped === 'home') {
                vm.stepThree.isSkiped = true;
            } else {
                vm.stepFour.isSkiped = true;
            }
            vm.selectedStep = vm.selectedStep + 1;
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
            if(totalPeople < 1) {
                ngNotify.set('Total number of travelers must be greater then 0.' , {
                    type : 'info'
                });
            }
            for(var i=1;i<=totalPeople;i++){
                vm.tabs.push(""+i);
            }
        }

        function sumNumberOfPeople(){
            var people = vm.stepOne.data.numberOfPeople;
            var totalPeople = 0;
            for(var num in people) {
                totalPeople += people[num];
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
            if(vm.playSport){
            	travelRisks.push(vm.stepOne.data.selectedSport);
            }
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

            if(!vm.stepThree.isSkiped) {
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
            } else {
                var homeInsuranceDTO = null;
            }

            console.log(homeInsuranceDTO);

            if(!vm.stepFour.isSkiped) {
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
            } else {
                var roadsideAssistanceInsuranceDTO = null;
            }

            console.log(roadsideAssistanceInsuranceDTO);

            var insurancePolicyDTO =
            {
                "totalValue": 50000,
                "dateOfIssue": vm.todayDate,
                "dateBecomeEffective": vm.stepOne.data.fromDate,
                "customers": customers,
                "internationalTravelInsurance": internationalTravelInsuranceDTO,
                "homeInsurance": homeInsuranceDTO,
                "roadsideAssistanceInsurance": roadsideAssistanceInsuranceDTO
            }
            insuranceService.createInsurancePolicy(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        toastr.success("Know you have your umbrella.",'<i>Success</i>');
                        console.log(response.data);
                        var paymentType = {};
						paymentType.label = vm.pay;

						var transactionDTO = {
							"timestamp" : "",

							"paymentType" : paymentType,
							"amount" : response.data.totalValue,
							"insurancePolicy" : response.data
						};

						insuranceService
								.createInquiry(transactionDTO)
								.then(
										function(response) {
											window.location = response.data.paymentUrl;

										});
                    }
                    else{
                    	toastr.error("Something got wrong with policy. Try again.",'<i>Error</i>');
                    }
                }
                
//                function(response) {
//					if (response.status == 200) {
//						ngNotify
//								.set(
//										'Know you have your umbrella.',
//										{
//											type : 'success'
//										});
//						console.log(response.data);
//
//						var paymentType = {};
//						paymentType.label = vm.pay;
//
//						var transactionDTO = {
//							"timestamp" : "",
//
//							"paymentType" : paymentType,
//							"amount" : response.data.totalValue,
//							"insurancePolicy" : response.data
//						};
//
//						insuranceService
//								.createInquiry(transactionDTO)
//								.then(
//										function(response) {
//											window.location = response.data.paymentUrl;
//
//										});
//
//					}
//				}
                
            	)
        }
    });
}());