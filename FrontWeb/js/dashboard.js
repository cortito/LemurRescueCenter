var app=angular.module('dashboard',[]);
app.controller('ctrlDashboard',function(){
	
	this.lemurList = lemurList;
});

var lemur1 = { 'id': '1', 'nom':'Jack','sexe': '1','dateNaissance':'2016-12-13','taille': '42', 'poids':'8.12', 'caracteristiques': 'tache blanche sur oreille gauche', 'etat': 'Vivant', 'famille': 'Jack', 'latitude': '-18.84392', 'longitude': '47.48191', 'parent1': null, 'parent2': null, 'dernierScan': '2016-12-16'};
var lemur2 = { 'id': '2', 'nom':'Nom2','sexe':'0','dateNaissance':'2016-04-13','taille': '42', 'poids':'3.4', 'caracteristiques': 'tache noire oeil gauche', 'etat': 'Vivant', 'famille': 'Jack', 'latitude': '-18.84392', 'longitude': '47.48191', 'parent1': null, 'parent2': null, 'dernierScan': '2016-12-16'};
var lemur3 = { 'id': '3', 'nom':'Nom3','sexe':'0','dateNaissance':'2012-04-01','taille': '45', 'poids':'2.7', 'caracteristiques': 'tache blanche oeil gauche', 'etat': 'Vivant', 'famille': 'Jack', 'latitude': '-18.84392', 'longitude': '47.48191', 'parent1': null, 'parent2': null, 'dernierScan': '2016-12-16'};

var lemurList = {lemur1, lemur2, lemur3};