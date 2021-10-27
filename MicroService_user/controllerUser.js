var express = require('express');
var router = express.Router();
var User=require('./user')
var joi=require('@hapi/joi');
const { valid } = require('@hapi/joi');
const Joi = require('@hapi/joi');
const jwt=require('jsonwebtoken');
const { JSONCookie } = require('cookie-parser');
const bcrypt=require('bcryptjs');
var dateFormat = require("dateformat");
const { GoogleAuth } = require('google-auth-library');
const {google} =require('googleapis');
const  jwtDecode  = require('jwt-decode');
const { response } = require('./app');
var controllerUser={}
  const schema=joi.object({
    FirstName:joi.string().required(),
    LastName:joi.string().required(),
    Email:joi.string().required().email(),
    Password:joi.string().required().min(8),
    Phone:joi.number(),
    image:joi.string(),
    
    
  });
  //Get All The Users
  controllerUser.getUser=async(req,res)=>{
    const user1=User.find()
    .then(users=>{res.status(200).json(users)})
    .catch(error=>{res.status(400).json(error)})
 
  }
  //Register
controllerUser.register=async(req,res)=>{
    var validation=schema.validate(req.body);
    const {error}=schema.validate(req.body);
 if(error) return res.send(error.details[0].message);
 //Checking if the email exist or not
 const EmailExists=await User.findOne({Email:req.body.Email});
 if(EmailExists) return res.status(400).send("Email already Exists");
 //Hash the password
const salt=await bcrypt.genSalt(10);
const hashedPassword=await bcrypt.hash(req.body.Password,salt);
if(!req.file){
    var user = new User(
        {
            FirstName: req.body.FirstName,
            LastName:req.body.LastName,
            Password:hashedPassword,
            Email:req.body.Email,
            Phone: req.body.Phone,

        }
    );
      }else{
         var user = new User(
          {
            FirstName: req.body.FirstName,
              LastName:req.body.LastName,
              Password:hashedPassword,
              Email:req.body.Email,
              BirthDate:req.body.BirthDate,
              Phone: req.body.Phone,
              image:req.file.filename
            }
      );
          }
    try{
       const savedUser=await user.save();
       console.log(user);
       //sendMailer.sendMail(user.Email);
       const token=jwt.sign({_id:user._id},process.env.TOKEN_SECRET);
       res.header('Authorization',token).send(token);
    }catch(err){
      res.status(400).send(err);

    }
};
//Login
const LogSchema=joi.object({
    Email:joi.string().required().email(),
    Password:joi.string().required().min(8),
    
})
controllerUser.login=async(req,res)=>{
   // var validation=LogSchema.validate(req.body);
    const {error1}=LogSchema.validate(req.body);
 if(error1) return res.send(error1.details[0].message);
 //Checking if the User exists
 const user=await User.findOne({Email:req.body.Email});
 if(!user) return res.status(400).send("Email is wrong");
 //Checking If Password is correct
 const validPassword=await bcrypt.compare(req.body.Password,user.Password);
 if(!validPassword) return res.status(400).send("Password is wrong");
//create JWT Token
//sendMailer.googleLogin(user.Email);

const token=jwt.sign({_id:user._id},process.env.TOKEN_SECRET);
res.header('Authorization',token).send(token);
};
//Update The user(To Complete)
controllerUser.Update=async(req,res)=>{
  var token =req.header('Authorization');
  var decodetoken=jwtDecode(token);
  if(!req.file){
   user1={
    
  }
  }else{
     user1=
     {
      
      image:req.file.filename
    }
  }
  try{
  User.findByIdAndUpdate(decodetoken._id,user1,
  function (err, data) {
    if (err)
        console.log(err);
    res.json(decodetoken._id);
});
}catch(err){console.log(err);}
}
//Delete User Connected
controllerUser.deleteUser=async(req,res)=>{
  var token =req.header('Authorization');
  var decodetoken=jwtDecode(token);
  console.log(decodetoken._id);
  User.deleteOne({_id:decodetoken._id})
  .then(function(){
    res.send("Deleted Suceed");
}).catch(function(error){
res.send("User d'ont deleted");
});
preference.deleteOne({user_id:decodetoken._id})
.then(function(){
  res.send("Deleted Suceed");
}).catch(function(error){
res.send("User d'ont deleted");
});
}

controllerUser.profile=async(req,res)=>{
  var token =req.header('Authorization');
  var decodetoken=jwtDecode(token);
  console.log(decodetoken);
  const user1=User.findOne({_id:decodetoken._id})
    .then(users=>{res.status(200).json(users);})
    .catch(error=>{res.status(400).json(error)})
 
  }

module.exports=controllerUser;

