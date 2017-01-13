/*jslint node: true */
'use strict';
/**
	/* DETAILS 
	**/  

angular.module('dashboard').directive('printGraph', function($compile, $http, $q) {
    return {
        scope: {
            ctrl: '=printGraph' //binding
        },
        link: function(scope, element, attrs) {
            var list = document.createElement("ul");
            list.className = "nav nav-tabs";
            list.role = "tablist";
            angular.element(document.getElementById('printGraphId')).append(list);

            var graph = document.createElement("div");
            graph.setAttribute("id", "tabs");
            graph.className = "tab-content";
            angular.element(document.getElementById('printGraphId')).append(graph);

            for(var test = 0; test<scope.ctrl.promises.length; test++)
            {
                scope.ctrl.finished.push(scope.ctrl.promises[test].promise);
            }

            $q.all(scope.ctrl.finished).then(function(){
                scope.ctrl.orderArrays(scope.ctrl.arrayPoidsAnnee, scope.ctrl.arrayLabelsAnnee, scope.ctrl.annees, scope.ctrl.moyenne);

                for(var j=scope.ctrl.anneeDebut; j<=scope.ctrl.anneeCourante; j++)
                {
                    var nouveauPoint = document.createElement("li");
                    if(j==scope.ctrl.anneeDebut)
                    {
                        nouveauPoint.className = "active";
                    }
                    nouveauPoint.setAttribute("data-toggle", "tab");
                    angular.element(list).append(nouveauPoint);

                    var link = document.createElement("a");
                    link.href = "#" + j;
                    link.innerHTML = j;

                    angular.element(nouveauPoint).append(link);

                    var contentTab = document.createElement("div");
                    contentTab.className = "tab-pane fade";
                    if(j==scope.ctrl.anneeDebut)
                    {
                        contentTab.className += " in active";
                    }
                    contentTab.setAttribute("role", "tabpanel");
                    contentTab.setAttribute("id", j);
                    angular.element(graph).append(contentTab);
                    scope.ctrl.onClick = function (points, evt) {};

                    var chartData = [scope.ctrl.arrayPoidsAnnee[j - scope.ctrl.anneeDebut], 
                                     scope.ctrl.moyenne[j - scope.ctrl.anneeDebut]];
                    var labels = scope.ctrl.arrayLabelsAnnee[j - scope.ctrl.anneeDebut];
                    scope.ctrl.chart =  {
                        labels: JSON.stringify(labels),
                        data: JSON.stringify(chartData)
                    };

                    scope.ctrl.options = JSON.stringify({
                        title: {
                            display: true,
                            text: 'Évolution du poids'
                        },    scales: {
                            xAxes: [{
                                ticks: {
                                    beginAtZero: true
                                },
                                labels: {
                                    userCallback: function(dataLabel, index) {
                                        return index % 2 === 0 ? dataLabel : '';
                                    }	
                                }}],
                            yAxes: [{
                                id: 'Poids',
                                type: 'linear',
                                display: true,
                                position: 'left',
                                ticks: {
                                    beginAtZero: true
                                }
                            }], 
                        }
                    });

                    scope.ctrl.series = "['Poids', 'Moyenne']";

                    var canvas = angular.element("<canvas></canvas>");
                    canvas.removeClass("ng-scope ng-isolate-scope");
                    canvas.addClass("chart chart-line");
                    canvas.attr("id", "canvasId");
                    canvas.attr("chart-data", scope.ctrl.chart.data);
                    canvas.attr("chart-labels", scope.ctrl.chart.labels);
                    canvas.attr("chart-options", scope.ctrl.options);
                    canvas.attr("chart-series", scope.ctrl.series);

                    $compile(canvas)(scope);

                    angular.element(contentTab).append(canvas);
                } 
            });

            $(".nav-tabs").on("click", "a", function (e) {
                e.preventDefault();
                if (!$(this).hasClass('tab-pane fade in active')) {
                    $(this).tab('show');
                }
            });
        }
    }

})

    .controller('detailsModalCtrl', function ($http, $scope, $rootScope, $uibModalInstance, lemur, $q, $timeout) {
    var $ctrl = this;
    $ctrl.lemur = lemur;

    $ctrl.arrayPoidsAnnee = []; 
    $ctrl.arrayLabelsAnnee = [];//doit contenir autant d'object (array) que d'années
    $ctrl.moyenne = [];
    $ctrl.annees = [];

    var url = $rootScope.addr + "FrontLRCWebService/rest/getPoids";
    var today = new Date();
    $ctrl.anneeDebut = 2015;
    $ctrl.anneeCourante = parseInt(today.getFullYear());
    $ctrl.moisCourant = parseInt(today.getMonth() + 1);

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.numero);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('Fermer');
    };

    $ctrl.getPoidsLemurien = function (lemur, annee) {
        var parameter = JSON.stringify({nom: $ctrl.lemur.nom});

        return $http.post(url, parameter)
            .success(function (res) {
            $ctrl.getPoids = res;

            var arrayAnnee = [];
            var labels = [];
            var donnees = [];
            var moyenne = [];

            var moy = $ctrl.getMoyenne($ctrl.getPoids).toString();
            var mois = 1;


            for(mois=1; mois<=12; mois++)
            {
                if (mois < 10)
                {
                    mois='0' + mois;
                }
                var date = mois + '/' + annee.toString().substr(2,2);
                labels.push(date);

                donnees.push($ctrl.remplirPoids(date, $ctrl.getPoids));
                moyenne.push(moy);
            }

            $ctrl.moyenne.push(moyenne);
            $ctrl.annees.push(annee);
            $ctrl.arrayPoidsAnnee.push(donnees);
            $ctrl.arrayLabelsAnnee.push(labels);

            ($ctrl.promises[annee - $ctrl.anneeDebut]).resolve();

            return res;
        })

            .error(function (res){
            console.log(res);
        });

    };

    $ctrl.getMoyenne = function(array){
        var somme = 0;
        var taille = 0;
        array.forEach(function(item){
            if(item.poids != 0.0){
                taille = taille + 1;
                somme = somme + parseFloat(item.poids);
            }
        });
        return (parseFloat(somme) / parseFloat(taille));
    }

    $ctrl.remplirPoids = function(date, arrayPoids){
        var currentPoids = null;
        arrayPoids.forEach(function (json) {
            if(json.date === date && json.poids != 0.0){
                currentPoids = json.poids;
            }
        });
        return currentPoids;
    };

    $ctrl.promises = [];
    $ctrl.finished = [];
    for(var j=$ctrl.anneeDebut; j<=$ctrl.anneeCourante; j++)
    {
        $ctrl.promises.push($q.defer());
        $ctrl.getPoidsLemurien($ctrl.lemur, j);
        ($ctrl.promises[j - $ctrl.anneeDebut]).promise.then(success);
    } 

    function success(data) {
        return data;
    }

    $ctrl.orderArrays = function(arrayPoids, arrayLabels, arrayAnnees, arrayMoyenne)
    {
        var replaceAnnee = 0;
        var replacePoids = 0;
        var replaceLabel = 0;
        var replaceMoyenne = 0;

        for(var j=0; j < arrayAnnees.length; j++)
        {
            for(var i=0; i < arrayAnnees.length - 1; i++)
            {
                if(arrayAnnees[i] > arrayAnnees[i+1])
                {
                    replaceAnnee = arrayAnnees[i];
                    arrayAnnees[i] = arrayAnnees[i+1];
                    arrayAnnees[i+1] = replaceAnnee;

                    replaceMoyenne = arrayMoyenne[i];
                    arrayMoyenne[i] = arrayMoyenne[i+1];
                    arrayMoyenne[i+1] = replaceMoyenne;

                    replaceLabel = arrayLabels[i];
                    arrayLabels[i] = arrayLabels[i+1];
                    arrayLabels[i+1] = replaceLabel;

                    replacePoids = arrayPoids[i];
                    arrayPoids[i] = arrayPoids[i+1];
                    arrayPoids[i+1] = replacePoids;
                }
            }
        }

        return {"poids": arrayPoids, "labels": arrayLabels, "annees": arrayAnnees, "moyenne": arrayMoyenne};
    }
});