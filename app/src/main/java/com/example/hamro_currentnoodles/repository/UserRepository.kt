package com.example.hamro_currentnoodles.repository

import com.example.hamro_currentnoodles.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository{

    fun login(  username:String,password:String,callback:(Boolean,String)->Unit)
    fun signup(username:String,password:String,callback:(Boolean,String,String)->Unit)
    fun addUserToDatabase(userId:String,userModel:UserModel,callback: (Boolean, String) -> Unit)
    fun forgetPassword(email: String,callback: (Boolean, String) -> Unit)
    fun getCurrentUser():FirebaseUser?
}
