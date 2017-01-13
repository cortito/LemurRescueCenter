/*jslint node: true */
'use strict';
var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'chart.js', 'ngRoute']);

/**
	 DASHBOARD
	**/
app.controller('dashboardCtrl', function ($scope, $rootScope, $http, $uibModal, $log, $document, $route) {
    var list = [];
    var $ctrl = this;
    $rootScope.addr = "http://192.168.1.20:1818/"
    //http://localhost:8080/FrontLRCWebService/rest/getLemurien
    //http://bab-laboratory.com/lrc/getAllLemurien.html
    $scope.getData = function () {
        $http.get($rootScope.addr + 'FrontLRCWebService/rest/getLemurien').then(function (res) {
            $scope.lemurList = res.data;
        });
    };
    $scope.getData();

    $scope.sortType = 'idDB';
    $scope.sortReverse = false;
    $scope.admin = 1;

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

});










