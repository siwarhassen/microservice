var createError = require('http-errors');
var express = require('express');
var path = require('path');
const dotenv=require('dotenv');
var logger = require('morgan');
var routes= require('./routing');
var serveStatic = require('serve-static')
const cors = require('cors');
var config =require('./database/mongodb.json');
var mongoose =require('mongoose');
var passport=require('passport');
var bodyParser = require('body-parser')
var cookieparser=require('cookie-parser');
var multer =require('multer');
dotenv.config();
var app = express();
app.use(cors());
app.use(bodyParser());
mongoose.connect(config.mongo.uri,
    {useNewUrlParser:true,useUnifiedTopology:true,useFindAndModify: false},
    ()=>console.log("connected todatabase"));

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.get('/uploads/:name',function(req, res){
  res.sendFile(__dirname+'/uploads/'+req.params.name);
});
app.use(routes);
app.use(passport.initialize());
app.use(passport.session());

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});


// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


module.exports = app;
