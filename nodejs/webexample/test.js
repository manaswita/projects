var express= require('express');
var path = require('path');
var bodyParser = require('body-parser');
var mysql      = require('mysql');
var pool      =    mysql.createPool({
    connectionLimit : 100, //important
    host     : 'localhost',
    user     : 'root',
    password : 'sa123',
    database : 'ideation',
    debug    :  false
});


/*connection.connect();

connection.query('SELECT * from t_user', function(err, rows, fields) {
  if (!err)
    console.log('The solution is: ', rows);
  else
    console.log('Error while performing Query.');
});
connection.end();*/

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



function handle_database(req,res) {
    
    pool.getConnection(function(err,connection){
        if (err) {
          connection.release();
          res.json({"code" : 100, "status" : "Error in connection database"});
          return;
        }   

        console.log('connected as id ' + connection.threadId);
        
        connection.query("select * from t_user",function(err,rows){
            connection.release();
            if(!err) {
              console.log(rows);
              res.render('test',{
                  title: "MansiTest",
                  items: rows
                });
                //res.json(rows);
            }           
        });

        connection.on('error', function(err) {      
              res.json({"code" : 100, "status" : "Error in connection database"});
              return;     
        });
  });
}


app.get('/', function(req, res) {

	//load data from DB here
	
  handle_database(req,res);
/*res.render('test',{
	title: "Mansi",
	items: todoItems
});*/
});

app.post('/add', function(req, res){
	var newItem= req.body.newItem;
	
	todoItems.push({
		id: todoItems.length + 1,
		desc: newItem
	});
	res.redirect('/');
});

app.get('*', function(req, res){
res.send(405,'Method not allowed');
});

app.listen(1337, function() {
console.log('working');
});
