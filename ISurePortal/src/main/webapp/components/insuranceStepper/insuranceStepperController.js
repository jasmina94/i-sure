(function(){

    'use strict';
    var app = angular.module('iSure');

    app.controller('insuranceStepperController', function ($scope, $q, $timeout) {

        var vm = this;

        vm.selectedStep = 0;
        vm.stepProgress = 1;
        vm.maxStep = 6;
        vm.showBusyText = false;
        vm.stepData = [
            { step: 1, completed: false, optional: false, data: {} },
            { step: 2, completed: false, optional: false, data: {} },
            { step: 3, completed: false, optional: false, data: {} },
            { step: 4, completed: false, optional: false, data: {} },
            { step: 5, completed: false, optional: false, data: {} },
            { step: 6, completed: false, optional: false, data: {} },
        ];

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
        }

        vm.moveToPreviousStep = function moveToPreviousStep() {
            if (vm.selectedStep > 0) {
                vm.selectedStep = vm.selectedStep - 1;
            }
        }

        vm.submitCurrentStep = function submitCurrentStep(stepData, isSkip) {
            var deferred = $q.defer();
            vm.showBusyText = true;
            console.log('On before submit');
            if (!stepData.completed && !isSkip) {
                //simulate $http
                $timeout(function () {
                    vm.showBusyText = false;
                    console.log('On submit success');
                    deferred.resolve({ status: 200, statusText: 'success', data: {} });
                    //move to next step when success
                    stepData.completed = true;
                    vm.enableNextStep();
                }, 1000)
            } else {
                vm.showBusyText = false;
                vm.enableNextStep();
            }
        }
        
        vm.fromDate = new Date();

        vm.minFromDate = new Date(
          vm.fromDate.getFullYear(),
          vm.fromDate.getMonth() - 2,
          vm.fromDate.getDate()
        );

        vm.maxFromDate = new Date(
          vm.fromDate.getFullYear(),
          vm.fromDate.getMonth() + 2,
          vm.fromDate.getDate()
        );
        
        vm.toDate = new Date();

        vm.minToDate = new Date(
          vm.toDate.getFullYear(),
          vm.toDate.getMonth() - 2,
          vm.toDate.getDate()
        );

        vm.maxToDate = new Date(
          vm.toDate.getFullYear(),
          vm.toDate.getMonth() + 2,
          vm.toDate.getDate()
        );
        
        vm.todayDate = new Date();

        vm.compareDates = function(date) {
		      var data_first_day = date.getDate();
		      var data_first_month = date.getMonth()+1;
		      var data_first_year = date.getFullYear();
		
		      var data_second_day = vm.todayDate.getDate();
		      var data_second_month = vm.todayDate.getMonth()+1;
		      var data_second_year = vm.todayDate.getFullYear();
		      
		      if(data_first_year == data_second_year && data_first_month == data_second_month && data_first_day == data_second_day){
		    	  return true;
		      }else if(data_first_year > data_second_year){
		          return true;
		        }else if (data_first_year == data_second_year){
		            if((data_first_month > data_second_month)){
		              return true;
		            }else if ((data_first_month < data_second_month)) {
		              return false;
		            }else{
		              if(data_first_day == data_second_day){
		                return false;
		              }else if (data_first_day > data_second_day){
		                return true;
		              }else{
		                return false;
		              }
		            }
		        }else{
		          return false;
		        }
        };
        
        vm.reons = ['Europe','Africa','America','Asia'];
        vm.selectedReon;
        vm.getSelectedReon = function() {
          if (vm.selectedReon !== undefined) {
            return vm.selectedReon;
          } else {
            return "Reons";
          }
        };
        vm.numberOfPeople = 4;
        
        vm.itemsCheckbox = ['less 18','18-60','above 60'];
        vm.selected = ['18-60'];
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
        
        vm.playSport = true;
        
        vm.sports = ['football','basketball','skiing','swimming'];
        vm.selectedSport;
        vm.getSelectedSport = function() {
          if (vm.selectedSport !== undefined) {
            return vm.selectedSport;
          } else {
            return "Sports";
          }
        };
        
        vm.amounts = ['10000e','20000e','30000e','40000e'];
        vm.selectedAmount;
        vm.getSelectedAmount = function() {
          if (vm.selectedAmount !== undefined) {
            return vm.selectedAmount;
          } else {
            return "Amounts";
          }
        };
        
        vm.carrier = true;
        
        vm.areas = ['less 30m2','30-50m2','above 50m2'];
        vm.selectedAreaOfHome;
        vm.getSelectedAreaOfHome = function() {
          if (vm.selectedAreaOfHome !== undefined) {
            return vm.selectedAreaOfHome;
          } else {
            return "Areas";
          }
        };
        
        vm.ageOfHome = ['less 2','5-10','above 10'];
        vm.selectedAgeOfHome;
        vm.getSelectedAgeOfHome = function() {
          if (vm.selectedAgeOfHome !== undefined) {
            return vm.selectedAgeOfHome;
          } else {
            return "Ages";
          }
        };
        
        vm.values = ['v1','v2','v3'];
        vm.selectedHomeValue;
        vm.getSelectedHomeValue = function() {
          if (vm.selectedHomeValue !== undefined) {
            return vm.selectedHomeValue;
          } else {
            return "Values";
          }
        };
        
        vm.risks = ['fire','flood','robbery'];
        vm.selectedRisk;
        vm.getSelectedRisk = function() {
          if (vm.selectedRisk !== undefined) {
            return vm.selectedRisk;
          } else {
            return "Risks";
          }
        };
        
        vm.assistances = ['a1','a2','a3'];
        vm.selectedAssistances;
        vm.getSelectedAssistance = function() {
          if (vm.selectedAssistance !== undefined) {
            return vm.selectedAssistance;
          } else {
            return "Assistances";
          }
        };
        
        vm.repairPrices = ['r1','r2','r3'];
        vm.selectedRepairPrice;
        vm.getSelectedRepairPrice = function() {
          if (vm.selectedRepairPrice !== undefined) {
            return vm.selectedRepairPrice;
          } else {
            return "Prices";
          }
        };
        
        vm.accommodationDays = ['a1','a2','a3'];
        vm.selectedAccommodationDay;
        vm.getSelectedAccommodationDay = function() {
          if (vm.selectedAccommodationDay !== undefined) {
            return vm.selectedAccommodationDay;
          } else {
            return "Days";
          }
        };
        
        vm.transports = ['t1','t2','t3'];
        vm.selectedTransport;
        vm.getSelectedTransport = function() {
          if (vm.selectedTransport !== undefined) {
            return vm.selectedTransport;
          } else {
            return "Transports";
          }
        };
        
        vm.pay = 'Pay1';
    });


}());