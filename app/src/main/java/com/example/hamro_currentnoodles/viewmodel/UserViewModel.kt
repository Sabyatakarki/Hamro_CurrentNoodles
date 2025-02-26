package com.example.hamro_currentnoodles.viewmodel

import com.example.hamro_currentnoodles.model.UserModel
import com.example.hamro_currentnoodles.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel(var repo: UserRepository) {

    fun login(
        username: String, password: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.login(username, password, callback)
    }


    fun signup(
        email: String,username:String, password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        repo.signup(email,username,password,callback)
    }

    fun addUserToDatabase(
        userId: String, userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addUserToDatabase(userId, userModel, callback)
    }

    fun forgetPassword(
        email: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.forgetPassword(email, callback)
    }

    fun getCurrentUser(): FirebaseUser? {
        return repo.getCurrentUser()
    }

}