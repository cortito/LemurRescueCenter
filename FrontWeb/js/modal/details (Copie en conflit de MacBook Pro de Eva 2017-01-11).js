/*jslint node: true */
'use strict';
/**
	/* DETAILS 
	**/  
angular.module('dashboard').controller('detailsModalCtrl', function ($http, $scope, $rootScope, $uibModalInstance, lemur) {
    var $ctrl = this;
    $ctrl.lemur = lemur;
    $ctrl.test = "test";
    //$scope.lemur = lemur;

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };

    var url = $rootScope.addr + "FrontLRCWebService/rest/getPoids";

    $ctrl.getPoids = [];
    $ctrl.labels = [];

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


    $ctrl.getPoidsLemurien = function (lemur) {
        var parameter = JSON.stringify({nom: lemur.nom});
        return $http.post(url, parameter)
            .success(function (response) {
            $ctrl.getPoids = response;

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
                    $ctrl.labels.push(date);

                    $ctrl.poids.push($ctrl.remplirPoids(date));
                }
            }
            for (var dernierMois = 1; dernierMois <= moisCourant; dernierMois ++)
            {
                if (dernierMois < 10)
                {
                    dernierMois = '0' + dernierMois;
                }
                var date = dernierMois + '/' + anneeCourante;
                $ctrl.labels.push(date);

                $ctrl.poids.push($ctrl.remplirPoids(date));
            }

            $ctrl.poids.forEach(function () {
                $ctrl.moyenne.push($ctrl.getMoyenne($ctrl.poids, $ctrl.poids.length));
            });
            return response;
        })

            .error(function (response){
            console.log(response);
        });
    };

    $scope.data = $ctrl.chart.data;
    $scope.series = $ctrl.chart.series;
    $scope.labels = $ctrl.chart.labels;
    //    $scope.getPoidsLemurien = function(lemur) {
    //        $http.get('https://trello-attachments.s3.amazonaws.com/58532b6c95e4b53eb15676bb/586b574e130cd59c7da0f167/7bb463a273eaa642593d483f7847e48d/PoidsMANOU.json').then(function(res) {
    //
    //        $scope.getPoids = res.data;
    //        $scope.getPoids.forEach(function(json){
    //            $scope.labels.push(json.date);
    //            $scope.poids.push(parseFloat(json.poids));
    //
    //        });
    //
    //        console.log($scope.poids.length);
    //
    //        $scope.poids.forEach(function(){
    //            $scope.moyenne.push($scope.getMoyenne($scope.poids, $scope.poids.length));
    //        });
    //
    //        console.log($scope.moyenne);
    //    });
    //
    //    }

    $ctrl.series = ['Poids', 'Moyenne'];
    $ctrl.moyenne = [];
    $ctrl.poids = [];
    //$ctrl.getPoids = $scope.getPoidsLemurien($ctrl.lemur);

    $ctrl.data = [ $ctrl.poids, $ctrl.moyenne ];
    $ctrl.onClick = function (points, evt) {};

    $ctrl.datasetOverride = [{ yAxisID: 'Poids' }];

    $ctrl.getMoyenne = function(array, length){
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

    $ctrl.remplirPoids = function(date){
        var currentPoids = null;
        $ctrl.getPoids.forEach(function (json) {
            if(json.date === date && json.poids != 0.0){
                currentPoids = json.poids;
            }
        });
        return currentPoids;
    };

    $ctrl.options = {
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

angular.module('dashboard').directive('customHtml', function($compile, $http){
    return {
        scope: {
            ctrl: '=customHtml' 
        },
        link: function(scope, element, attrs) {
            $http.get('modal/evolution.html').then(function (result) {
                console.log(scope.ctrl);
                console.log(scope.ctrl.lemur);
                var $ctrl = scope.ctrl;

                $ctrl.ecrit = "oui j'écris";

                var annees = ["2015", "2016", "2017"];
                var test = document.createElement("ul");
                test.className = "nav nav-tabs";
                test.role = "tablist";
                angular.element(document.getElementById('graphs')).append(test);

                var graph = document.createElement("div");
                graph.setAttribute("id", "tabs");
                graph.className = "tab-content";
                angular.element(document.getElementById('graphs')).append(graph);

                $ctrl.getPoids = $ctrl.getPoidsLemurien($ctrl.lemur);
                $ctrl.getPoids.then(function(response){
                    //////

                    for(var i=0; i<annees.length; i++)
                    {
                        var nouveauPoint = document.createElement("li");
                        if(i==0)
                        {
                            nouveauPoint.className = "active";
                        }
                        nouveauPoint.setAttribute("role", "presentation");
                        nouveauPoint.setAttribute("data-toggle", "tab");
                        angular.element(test).append(nouveauPoint);

                        var link = document.createElement("a");
                        link.href = "#" + annees[i];
                        link.innerHTML = annees[i];

                        angular.element(nouveauPoint).append(link);


                        var contentTab = document.createElement("div");
                        contentTab.className = "tab-pane fade";
                        if(i==0)
                        {
                            contentTab.className += " in active";
                        }
                        contentTab.setAttribute("role", "tabpanel");
                        contentTab.setAttribute("id", annees[i]);

                        var canv = document.createElement("canvas");
                        canv.className = "chart chart-line";
                        // canv.setAttribute("id", "canvasId");
                        scope.data = [2, 4];
                        scope.labels = [1, 2];
                        scope.series = $ctrl.series;
                        canv.setAttribute("chart-data", "data");
                        canv.setAttribute("chart-labels", "labels");
                        canv.setAttribute("chart-series", "series");
                        //                        canv.setAttribute("chart-labels", $ctrl.labels);
                        //                        canv.setAttribute("chart-series", $ctrl.series);
                        //                        canv.setAttribute("chart-options", "options");
                        //                        canv.setAttribute("chart-dataset-override", "datasetOverride");
                        //                        canv.setAttribute("chart-click", $ctrl.onClick);
                        //                        canv.labels = $ctrl.labels;
                        //                        canv.data = $ctrl.data;

                        angular.element(contentTab).append(canv);


                        angular.element(graph).append(contentTab);
                    }

                    //////
                });



                $('.nav-tabs a').click(function(){
                    $(this).tab('show');
                }) 
                //          <canvas id="line" class="chart chart-line" chart-data="data" chart-labels="labels" chart-series="series" chart-options="options" chart-dataset-override="datasetOverride" chart-click="onClick">
                //			</canvas>

                //         var my_div = document.createElement("div");
                //          my_div.innerHTML = "ici";
                //        angular.element(document.getElementById('testInclude')).append(my_div);
                //element.replaceWith($compile(result.data)(scope));



                var url = "http://192.168.1.102:8080/FrontLRCWebService/rest/getPoids";
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
                element.replaceWith($compile(result.data)(scope));


            });


        }
    }
})


app.controller('testModalCtrl', function ($http, $scope, $rootScope, $uibModalInstance, lemur) {

    var $ctrl = this;
    $ctrl.data = [1, 2, 3];

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };

    $scope.test = "test";
    
//    $scope.data = $ctrl.chart.data;
//    $scope.series = $ctrl.chart.series;
//    $scope.labels = $ctrl.chart.labels;
    
    console.log($ctrl)
//    console.log("labels")
//    console.log($scope.labels);
    //$scope.lemur = lemur;
});

angular.module('dashboard').directive('testHtml', function($compile, $http){
    return {
        scope: {
            ctrl: '=testHtml'// here, using the same name as the attribute
        },
        link: function(scope, element, attrs) {
            //console.log(scope.ctrl)

            //var canvas = document.createElement("canvas");

            //            sangular.element().append(canvas);


            var canvas = angular.element("<canvas></canvas>");
            canvas.attr("id", "canvasId");
            canvas.attr("chart-data", "data");
            canvas.attr("chart-labels", "labels");
            $compile(canvas)(scope);
            angular.element(document.getElementById("dir")).append(canvas);

            scope.chart = "oui";
            
            scope.ctrl.chart =    {
                name: 'Chart 1',
                type: 'Doughnut',
                labels: ['EVSC', 'ISB'],
                data: [13, 44]
            };

            //            scope.$watch('ctrl', function() { scope.dataRecup = scope.ctrl.data; 
            //                                            console.log(scope.dataRecup)
            //                                            });
            //                                                   scope.$watch('attr4',      function() {  });
            //                                                                                         }
        }
    }
});

