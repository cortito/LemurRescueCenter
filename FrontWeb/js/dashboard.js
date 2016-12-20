var app = angular.module('dashboard', ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);

app.controller('ctrlDashboard',function($scope, $http){
	var list = [];
	
	$http.get('../FrontWeb/content/lemurList.json').then(function(res) {
        $scope.lemurList = res.data;
        console.log($scope.lemurList);
        });
        
        $scope.sortType = 'id';
        $scope.sortReverse = false;
        
        $scope.admin = 1;
});