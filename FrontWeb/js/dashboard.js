var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);

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
	
	$http.get('http://bab-laboratory.com/lrc/getAllLemurien.html').then(function(res) {
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
  
  $http.get('http://bab-laboratory.com/lrc/getPoidsByName.html').then(function(res) {

 		$scope.poids = res.data;
        console.log($scope.poids);

        });

 
  
  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.numero);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('Fermer');
  };
});



