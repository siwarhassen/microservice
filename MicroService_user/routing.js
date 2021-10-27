var express = require('express');
const { route } = require('./app');
var router = express.Router();
var multer =require('multer');
var passport=require('passport');
var verify =require('./userManager/VerifyToken');
//var authenticate=require('../controllers/userManager/authenticate');
//var forgotPassword=require('../controllers/userManager/forgotPassword');
var controllerUser=require( './controllerUser');
//var sendMailer=require('../controllers/userManager/sendMailer');
var storage = multer.diskStorage({   
  destination: function(req, file, cb) { 
     cb(null, 'uploads/');    
  }, 
  filename: function (req, file, cb) { 
      const fileName = file.originalname.toLowerCase().split(' ').join('-');
     cb(null , file.originalname);   
  }
});
const upload=multer({storage:storage});
//Routes for userController
router.post('/register',upload.single('image'),controllerUser.register);
router.post('/login',controllerUser.login);
router.put('/updateUser',upload.single('image'),verify,controllerUser.Update);
router.get('/alluser',controllerUser.getUser);
router.get('/profile',verify,controllerUser.profile);
router.delete('/deleteUser',verify,controllerUser.deleteUser);
//router.post('/forgotPassword',forgotPassword.forgotPassword);
//router.post('/resendPassword',forgotPassword.resendPassword);
module.exports = router;
