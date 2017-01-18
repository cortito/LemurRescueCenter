/*jslint node: true */
'use strict';
/**
	/* AJOUT 
	**/  
angular.module('dashboard').controller('ajoutModalCtrl', function ($http, $scope, $uibModalInstance,$rootScope, lemur) 	{
    var $ctrl = this;
    $scope.empty = {};

    $scope.add = function(lemur) {
        $scope.empty = angular.copy(lemur);

        var url = $rootScope.addr + "FrontLRCWebService/rest/addLemurien"; 
        var parameter = JSON.stringify(lemur);
        return $http.post(url, parameter)

            .success(function (res) {
            console.log(form);
            $rootScope.$broadcast('refresh');
            alert(res.commentaire);

            if(res.response)
            {
                $ctrl.ok();
            }
            return res;
        })

            .error(function (response) {
            console.log(response);
        });
    };

    $scope.reset = function(form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
        $scope.lemur = angular.copy($scope.empty);
    };

    $scope.reset();
    
    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };
});