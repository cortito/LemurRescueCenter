<<<<<<< HEAD
var express = require("express");
var app = express();

app.get("/", function(req, res) {
	res.sendFile(__dirname+'/index.html')
});

app.get("/auth", function(req, res) {
	res.sendFile(__dirname+'/auth.html')
});

app.get("/status", function(req, res) {
 res.send("OK");
});

var port = 1337;
app.listen(port, function() {
console.log("Express Node.js server running on port 1337.");
});
=======
var express = require("express");
var app = express();

app.get("/", function(req, res) {
	res.sendFile(__dirname+'/index.html')
});

app.get("/auth", function(req, res) {
	res.sendFile(__dirname+'/auth.html')
});

app.get("/status", function(req, res) {
 res.send("OK");
});

var port = 1337;
app.listen(port, function() {
console.log("Express Node.js server running on port 1337.");
});
>>>>>>> DeveloppMobile
