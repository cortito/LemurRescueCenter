/*jslint node: true */
'use strict';
/**
	/* SUPPRESSION 
	**/  
angular.module('dashboard').controller('suppressionModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 
                                       {
    var $ctrl = this;
    $ctrl.lemur = lemur;

    $scope.delete = function(id) {        
        var url = $rootScope.addr + "FrontLRCWebService/rest/deleteLemurien"; 
        var parameter = angular.toJson(id);
        return $http.post(url, parameter)

            .success(function (res) {
            alert(res.commentaire);

            if(res.response)
            {
                $ctrl.ok();
            }

            $rootScope.$broadcast('refresh');
            return res;
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
