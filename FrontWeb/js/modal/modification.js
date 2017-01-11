/*jslint node: true */
'use strict';
/**
	/* MODIFICATION 
	**/  
angular.module('dashboard').controller('modificationModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 	{
    var $ctrl = this;
    $scope.lemur = lemur;
    $scope.ancien = angular.copy(lemur);

    $scope.update = function(lemur) {        
        var url = $rootScope.addr + "FrontLRCWebService/rest/updateLemurien";
        var parameter = angular.toJson(lemur);
        return $http.post(url, parameter)

            .success(function (response) {
            $ctrl.ok();
            alert("Lémurien modifié");
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
