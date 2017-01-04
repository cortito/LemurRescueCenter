var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'chart.js']);

app.controller('dashboardCtrl',function($scope, $http, $uibModal, $log, $document){
	var list = [];
	//http://localhost:8080/FrontLRCWebService/rest/getLemurien
  //http://bab-laboratory.com/lrc/getAllLemurien.html
	$http.get('http://localhost:8080/FrontLRCWebService/rest/getLemurien').then(function(res) {
 		$scope.lemurList = res.data;
	});
        
    $scope.sortType = 'idDB';
    $scope.sortReverse = false;
    $scope.admin = 1;
    var $ctrl = this;
    $scope.numeroFictif = 23;
  
      
    $scope.open = function (size, lemur, type, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
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
 
 
 
});
   
 
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


  $scope.getPoidsLemurien = function (lemur) {
  var parameter = JSON.stringify({nom: lemur.nom});
    return $http.post(url, parameter)

        .success(function (response) {
        	$scope.getPoids = response;
        	$scope.getPoids.forEach(function(json){
    			$scope.labels.push(json.date);
				$scope.poids.push(parseFloat(json.poids));
        	});
        	$scope.poids.forEach(function(){
				$scope.moyenne.push($scope.getMoyenne($scope.poids, $scope.poids.length));
			});
            return response;
        })

        .error(function (response)
        {
        	console.log(response);
        });
	};
	
	$scope.labels = []
	$scope.series = ['Poids', 'Moyenne'];
	$scope.poids = [];
	$scope.moyenne = [];

	$scope.getPoids = $scope.getPoidsLemurien($ctrl.lemur);

  	$scope.data = [ $scope.poids, $scope.moyenne];
    $scope.onClick = function (points, evt) {};
    
  $scope.datasetOverride = [{ yAxisID: 'Poids' }];
  
  function isFloat(n){
    return Number(n) === n && n % 1 !== 0;
	}
  
   $scope.getMoyenne = function(array, length){
        var somme = 0;
        var taille = 0;
        	array.forEach(function(item)
        	{
        	
			if(isFloat(item))
        		{
        		taille = taille + 1;
        		somme = somme + item;}
        	});
        	return somme / taille;
        }
  
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


app.controller('ajoutModalCtrl', function ($http, $scope, $uibModalInstance, lemur) {
  var $ctrl = this;
  $scope.empty = {};

      $scope.add = function(lemur) {
        $scope.empty = angular.copy(lemur);
        
        var url = "http://localhost:8080/FrontLRCWebService/rest/addLemurien"; 
        var parameter = JSON.stringify(lemur);
    		return $http.post(url, parameter)

        .success(function (response) {
        	console.log(response);
            return response;
        })

        .error(function (response)
        {
        	console.log(response);
        });
      };

      $scope.reset = function() {
        $scope.user = angular.copy($scope.empty);
      };

      $scope.reset();
   
  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.numero);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('Fermer');
  };
  });
