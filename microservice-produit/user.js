var  mongoose=require('mongoose');
var Schema =mongoose.Schema;
var User=new Schema({
    Phone:{
        type:Number,
    },
    image:{
        type:String,
    },
    FirstName: {
        type:String,
        required:true,
        
    },
    LastName:{
        type:String,
                required:true,
        
    },
    Password:{
        type:String,
        
    },
    Email: {
        type:String,
        required:true,
        
    }
    
    


    
});
module.exports =mongoose.model('user',User);
