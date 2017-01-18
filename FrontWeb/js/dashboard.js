/*jslint node: true */
'use strict';
var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'chart.js','angularUtils.directives.dirPagination']);

/**
	 DASHBOARD
	**/
app.controller('dashboardCtrl', function ($scope, $rootScope, $http, $uibModal, $document) {
    var list = [];
    var $ctrl = this;
    $rootScope.addr = "http://192.168.1.20:1818/";
    $scope.getData = function () {
        $http.get($rootScope.addr + 'FrontLRCWebService/rest/getLemurien').then(function (res) {
            $scope.lemurList = res.data;
            
        });
    };


    $scope.getData();

    $scope.sortType = 'idDB';
    $scope.sortReverse = false;

    $scope.open = function (size, lemur, type, parentSelector) {
        var parentElem = parentSelector ? angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'modal/' + type + 'Modal.html',
            controller: type + 'ModalCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                lemur: function () {
                    return lemur;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $ctrl.selected = selectedItem;
        }, function () {
            // quit modal
        });
    };

    $scope.$on('refresh', function (event) {
        $scope.getData();
    });
    
     $scope.range = function (size,start, end) {
        var ret = [];        
        console.log(size,start, end);
                      
        if (size < end) {
            end = size;
            start = size-$scope.gap;
        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }        
         console.log(ret);        
        return ret;
    };
    ///////
    
    


});










