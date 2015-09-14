var express= require('express');
var path = require('path');
var bodyParser = require('body-parser');

var app =  express();
//config app

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

//use middleware

app.use(bodyParser());
app.use(express.static(path.join(__dirname, 'bower-components')));

//define routes

var todoItems=[
		{id:1, desc:'foo'},
		{id:2, desc:'bar'},
		{id:3, desc:'baz'},
	];

app.get('/', function(req, res) {
	//load data from DB here
res.render('index',{
	title: "Mansi",
	items: todoItems
});
});

app.post('/add', function(req, res){
	var newItem= req.body.newItem;
	todoItems.push({
		id: todoItems.length + 1,
		desc: newItem
	});
	res.redirect('/');
});

app.get('*', function(req,res){
res.send(405,'Method not allowed');
});

app.listen(1337, function() {
console.log('working');
});
