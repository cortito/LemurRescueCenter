var app=angular.module('dashboard',[]);
app.controller('ctrlDashboard',function($scope, $http, $q){
	var list = [];
	
	
	$http.get('../FrontWeb/content/lemurList.json').then(function(res) {
        $scope.lemurList = res.data;
        console.log($scope.lemurList);
        });
});