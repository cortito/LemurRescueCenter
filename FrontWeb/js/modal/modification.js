/*jslint node: true */
'use strict';
/**
	/* MODIFICATION 
	**/  
angular.module('dashboard').controller('modificationModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 	{
    var $ctrl = this;
    $scope.lemur = lemur;
    $scope.empty = {};

    $scope.update = function(lemur) {        
        var url = $rootScope.addr + "FrontLRCWebService/rest/updateLemurien";
        var parameter = angular.toJson(lemur);
        console.log(lemur);
        return $http.post(url, parameter)

            .success(function (res) {

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

     $scope.reset = function() {
        $scope.lemur = angular.copy($scope.empty);
    };


    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };
});
