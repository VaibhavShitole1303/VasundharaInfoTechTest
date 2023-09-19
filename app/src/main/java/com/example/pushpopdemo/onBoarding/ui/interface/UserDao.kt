package com.example.pushpopdemo.onBoarding.ui.`interface`

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pushpopdemo.onBoarding.model.User


@Dao
interface UserDao {

    @Insert
    suspend fun insert(register: User)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email")
    suspend fun getUserEmail(email: String): User?



}
