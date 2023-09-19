package com.example.pushpopdemo.onBoarding.repository



import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pushpopdemo.onBoarding.model.User
import com.example.pushpopdemo.onBoarding.ui.`interface`.UserDao

class RegisterRepository(private val dao: UserDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: User) {
        return dao.insert(user)
    }

    suspend fun getUserEmail(userEmail: String):User?{
        return dao.getUserEmail(userEmail)
    }

}