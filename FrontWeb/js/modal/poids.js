/*jslint node: true */
'use strict';
/**
	/* WEIGHT LEMURIEN
	**/  
angular.module('dashboard').controller('poidsModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 
               {
    var $ctrl = this;
    $ctrl.lemur = lemur;

    var today = new Date();
    var mm = today.getMonth()+1; //January is 0!
    var yy = today.getFullYear().toString().substr(2, 2);

    if(mm<10) {
        mm='0'+mm
    } 

    $scope.data = {};
    $scope.data.date = mm + '/' + yy;
    $scope.data.poids = '';

    $scope.addWeight = function(nom, data) {     
        var url = $rootScope.addr + "FrontLRCWebService/rest/addPoids";
        var parameter = JSON.stringify({ nom: nom, date: data.date, poids: data.poids});
        console.log(parameter);

        return $http.post(url, parameter)

            .success(function (response) {
            $ctrl.ok();
            alert("Poids ajouté");
            $rootScope.$broadcast('refresh');
            return response;
        })

            .error(function (response)
                   {
            console.log(response);
        });
    };

    $scope.deleteWeight = function(nom, data) {     
        var url = "http://192.168.1.101:8080/FrontLRCWebService/rest/deletePoids";
        var parameter = JSON.stringify({ nom: nom, date: data.date, poids: data.poids});
        console.log(parameter);

        return $http.post(url, parameter)

            .success(function (response) {
            $ctrl.ok();
            alert("Poids supprimé");
            $rootScope.$broadcast('refresh');
            return response;
        })

            .error(function (response)
                   {
            console.log(response);
        });
    };


    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };
});
