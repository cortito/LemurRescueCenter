var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'chart.js']);

/*
app.controller('dashboardCtrl',function($scope, $http){
	var list = [];
	
	$http.get('http://bab-laboratory.com/lrc/getAllLemurien.html').then(function(res) {

 		$scope.lemurList = res.data;
        console.log($scope.lemurList);

        });
        
        $scope.sortType = 'idDB';
        $scope.sortReverse = false;
        
        $scope.admin = 1;
});
*/

app.controller('dashboardCtrl',function($scope, $http, $uibModal, $log, $document){
	var list = [];
	//http://localhost:8080/FrontLRCWebService/rest/getLemurien
  //http://bab-laboratory.com/lrc/getAllLemurien.html
	$http.get('http://localhost:8080/FrontLRCWebService/rest/getLemurien').then(function(res) {
 		$scope.lemurList = res.data;
    	console.log($scope.lemurList);
	});
        
    $scope.sortType = 'idDB';
    $scope.sortReverse = false;
    $scope.admin = 1;
    var $ctrl = this;
    $scope.numeroFictif = 23;
  
      
    $scope.open = function (size, lemur, parentSelector) {
    var parentElem = parentSelector ? 
      angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
    var modalInstance = $uibModal.open({
      animation: $ctrl.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstanceCtrl',
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
      $log.info('Modal dismissed at: ' + new Date());
    });
 };
 
 
 
});
   
 
app.controller('ModalInstanceCtrl', function ($http, $scope, $uibModalInstance, lemur) {
  var $ctrl = this;
  $ctrl.lemur = lemur;
  
   
  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.numero);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('Fermer');
  };
  
   $http.get('https://trello-attachments.s3.amazonaws.com/58532b6c95e4b53eb15676bb/586b574e130cd59c7da0f167/b27300ac81659c46c095015b83e6dd63/PoidsNAME.json').then(function(res) {

 		$scope.getPoids = res.data;
 		$scope.getPoids.forEach(function(json){
 		 $scope.labels.push(json.date);
 		 $scope.poids.push(parseFloat(json.poids));
		 
 		});

        console.log($scope.poids.length);

$scope.poids.forEach(function(){
$scope.moyenne.push($scope.getMoyenne($scope.poids, $scope.poids.length));
});
 
 		console.log($scope.moyenne);
        });


 
$scope.labels = []
  $scope.series = ['Poids', 'Moyenne'];
  $scope.poids = [];
  $scope.moyenne = [];
  $scope.data = [ $scope.poids, $scope.moyenne];
    $scope.onClick = function (points, evt) {
    console.log(points, evt);
  };
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


			console.log(item);
        	});
        	console.log(somme + " long: " + length);
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

