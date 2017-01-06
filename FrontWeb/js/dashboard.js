/*jslint node: true */
'use strict';
var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'chart.js', 'ngRoute']);

/**
	 DASHBOARD
	**/
app.controller('dashboardCtrl', function ($scope, $http, $uibModal, $log, $document, $route) {
    var list = [];
    var $ctrl = this;
    //http://localhost:8080/FrontLRCWebService/rest/getLemurien
    //http://bab-laboratory.com/lrc/getAllLemurien.html
    $scope.getData = function () {
        $http.get('http://localhost:8080/FrontLRCWebService/rest/getLemurien').then(function (res) {
            $scope.lemurList = res.data;
        });
    };
    $scope.getData();

    $scope.sortType = 'idDB';
    $scope.sortReverse = false;
    $scope.admin = 1;

    $scope.numeroFictif = 23;


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


/**
	DETAILS
	**/
app.controller('detailsModalCtrl', function ($http, $scope, $uibModalInstance, lemur) {
    var $ctrl = this;
    $ctrl.lemur = lemur;

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };

    var url = "http://localhost:8080/FrontLRCWebService/rest/getPoids";

    $scope.getPoids = [];



    $scope.labels = [];

    var today = new Date();
    var anneeCourante = parseInt(today.getFullYear().toString().substr(2, 2));
    var moisCourant = parseInt(today.getMonth() + 1);



    function checkForValue(json, value) {
	    for (key in json) {
	        if (typeof (json[key]) === "object") {
	            return checkForValue(json[key], value);
	        } else if (json[key] === value) {
	            return true;
	        }
	    }
	    return false;
	}

    $scope.getPoidsLemurien = function (lemur) {
        var parameter = JSON.stringify({nom: lemur.nom});
        return $http.post(url, parameter)
            .success(function (response) {
            $scope.getPoids = response;

		    //De 01/15 à aujourd'hui -> échelle
		    for (var annee=15; annee < anneeCourante; annee++)
		    {
		        for (var mois = 1; mois <= 12; mois++)
		        {
		            if (mois < 10)
		            {
		                mois='0' + mois;
		            }
		            var date = mois + '/' + annee;
		            $scope.labels.push(date);

					$scope.poids.push($scope.remplirPoids(date));
		        }
		    }
		    for (var dernierMois = 1; dernierMois <= moisCourant; dernierMois ++)
		    {
		        if (dernierMois < 10)
		        {
		            dernierMois = '0' + dernierMois;
		        }
		        var date = dernierMois + '/' + anneeCourante;
		        $scope.labels.push(date);
		        
				$scope.poids.push($scope.remplirPoids(date));

		    }

            $scope.poids.forEach(function () {
                $scope.moyenne.push($scope.getMoyenne($scope.poids, $scope.poids.length));
            });
            return response;
        })

            .error(function (response){
            console.log(response);
        });
    };

    $scope.series = ['Poids', 'Moyenne'];
    $scope.moyenne = [];
    $scope.poids = [];
    $scope.getPoids = $scope.getPoidsLemurien($ctrl.lemur);

    $scope.data = [ $scope.poids, $scope.moyenne ];
    $scope.onClick = function (points, evt) {};

    $scope.datasetOverride = [{ yAxisID: 'Poids' }];

    $scope.getMoyenne = function(array, length){
        var somme = 0;
        var taille = 0;
        array.forEach(function(item){
            if(item != null){
                taille = taille + 1;
                somme = somme + parseFloat(item);
            }
        });
        return (parseFloat(somme) / parseFloat(taille));
    }

    $scope.remplirPoids = function(date){
		var currentPoids = null;
		$scope.getPoids.forEach(function (json) {
			if(json.date === date && json.poids != 0.0){
				currentPoids = json.poids;
			}
		});
		return currentPoids;
    };

    $scope.options = {
        title: {
            display: true,
            text: 'Évolution du poids'
        },    scales: {
            xAxes: [{
                labels: {
                    userCallback: function(dataLabel, index) {
                        return index % 2 === 0 ? dataLabel : '';
                    }	
                }
            }],
            yAxes: [
                {
                    id: 'Poids',
                    type: 'linear',
                    display: true,
                    position: 'left',
                    ticks: {
                        beginAtZero: true
                    }
                }
            ], 
        }
    };

});


/**
	/* ADD LEMURIEN
	**/
app.controller('ajoutModalCtrl', function ($http, $scope, $route, $rootScope, $uibModalInstance, lemur) 	{
    var $ctrl = this;
    $scope.empty = {};

    $scope.add = function(lemur) {
        $scope.empty = angular.copy(lemur);

        var url = "http://localhost:8080/FrontLRCWebService/rest/addLemurien"; 
        var parameter = JSON.stringify(lemur);
        return $http.post(url, parameter)

            .success(function (response) {
            $ctrl.ok();
            $rootScope.$broadcast('refresh');
            return response;
        })

            .error(function (response)
                   {
            console.log(response);
        });
    };

    $scope.reset = function() {
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


/**
	/* MODIFICATION LEMURIEN
	**/ 
app.controller('modificationModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 	{
    var $ctrl = this;
    $scope.lemur = lemur;
    $scope.ancien = angular.copy(lemur);

    $scope.update = function(lemur) {        
        var url = "http://localhost:8080/FrontLRCWebService/rest/updateLemurien";
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

/**
	/* DELETE LEMURIEN
	**/  
app.controller('suppressionModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 
               {
    var $ctrl = this;
    $ctrl.lemur = lemur;

    $scope.delete = function(id) {        
        var url = "http://localhost:8080/FrontLRCWebService/rest/deleteLemurien"; 
        var parameter = angular.toJson(id);
        return $http.post(url, parameter)

            .success(function (response) {
            $ctrl.ok();
            alert("Lémurien supprimé");
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


/**
	/* WEIGHT LEMURIEN
	**/  
app.controller('poidsModalCtrl', function ($http, $scope, $uibModalInstance, $rootScope, lemur) 
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

    $scope.addWeight = function(nom, data) {     

        var url = "http://localhost:8080/FrontLRCWebService/rest/addPoids";
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


    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };
});



