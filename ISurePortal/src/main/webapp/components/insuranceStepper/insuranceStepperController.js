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
                                selectedReon: vm.travelRisks['Region'][0],
                                numberOfPeople: 4,
                                playSport: vm.travelRisks['Sport'][0],
                                ageGroup: vm.travelRisks['Age'][0],
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

        vm.calculateDays = function calculateDays() {
            var timeDiff = Math.abs(vm.stepData[0].data.fromDate.getTime() - vm.stepData[0].data.toDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            vm.stepData[0].data.durationInDays = diffDays;
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
                                            area: vm.homeRisks['Surface area'][0],
                                            age: vm.homeRisks['Property age'][0],
                                            value: vm.homeRisks['Estimated value of property'][0],
                                            risk: vm.homeRisks['Property risks'][0]
                                        }
                                    };
                                }
                            })
                    }
                } else if (what === 'car') {

                }
            }
            //validation goes here , if its satisfied then it's this
            vm.stepOne.notCompleted = false;
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

        vm.reons = ['Europe', 'Africa', 'America', 'Asia'];
        vm.itemsCheckbox = ['less 18', '18-60', 'above 60'];
        vm.toggle = function (item, list) {
            var idx = list.indexOf(item);
            if (idx > -1) {
                list.splice(idx, 1);
            }
            else {
                list.push(item);
            }
        };

        vm.exists = function (item, list) {
            return list.indexOf(item) > -1;
        };

        vm.playSport = false;

        vm.sports = ['football', 'basketball', 'skiing', 'swimming'];

        vm.amounts = ['10000e', '20000e', '30000e', '40000e'];

        vm.carrier = true;

        vm.areas = ['less 30m2', '30-50m2', 'above 50m2'];
        vm.selectedAreaOfHome;
        vm.getSelectedAreaOfHome = function () {
            if (vm.selectedAreaOfHome !== undefined) {
                return vm.selectedAreaOfHome;
            } else {
                return "Areas";
            }
        };

        vm.ageOfHome = ['less 2', '5-10', 'above 10'];
        vm.selectedAgeOfHome;
        vm.getSelectedAgeOfHome = function () {
            if (vm.selectedAgeOfHome !== undefined) {
                return vm.selectedAgeOfHome;
            } else {
                return "Ages";
            }
        };

        vm.values = ['v1', 'v2', 'v3'];
        vm.selectedHomeValue;
        vm.getSelectedHomeValue = function () {
            if (vm.selectedHomeValue !== undefined) {
                return vm.selectedHomeValue;
            } else {
                return "Values";
            }
        };

        vm.risks = ['fire', 'flood', 'robbery'];
        vm.selectedRisk;
        vm.getSelectedRisk = function () {
            if (vm.selectedRisk !== undefined) {
                return vm.selectedRisk;
            } else {
                return "Risks";
            }
        };

        vm.assistances = ['a1', 'a2', 'a3'];
        vm.selectedAssistances;
        vm.getSelectedAssistance = function () {
            if (vm.selectedAssistance !== undefined) {
                return vm.selectedAssistance;
            } else {
                return "Assistances";
            }
        };

        vm.repairPrices = ['r1', 'r2', 'r3'];
        vm.selectedRepairPrice;
        vm.getSelectedRepairPrice = function () {
            if (vm.selectedRepairPrice !== undefined) {
                return vm.selectedRepairPrice;
            } else {
                return "Prices";
            }
        };

        vm.accommodationDays = ['a1', 'a2', 'a3'];
        vm.selectedAccommodationDay;
        vm.getSelectedAccommodationDay = function () {
            if (vm.selectedAccommodationDay !== undefined) {
                return vm.selectedAccommodationDay;
            } else {
                return "Days";
            }
        };

        vm.transports = ['t1', 't2', 't3'];
        vm.selectedTransport;
        vm.getSelectedTransport = function () {
            if (vm.selectedTransport !== undefined) {
                return vm.selectedTransport;
            } else {
                return "Transports";
            }
        };

        vm.pay = 'Pay1';
    });


}());