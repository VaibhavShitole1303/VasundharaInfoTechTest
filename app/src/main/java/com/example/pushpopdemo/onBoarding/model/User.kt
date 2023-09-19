package com.example.pushpopdemo.onBoarding.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "Register_users_table")
data class User(
    @ColumnInfo(name = "email")
    var userEmail: String,

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,





    @ColumnInfo(name = "password_text")
    var passwrd: String
)