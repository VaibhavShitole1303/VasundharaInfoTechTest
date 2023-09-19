package com.example.pushpopdemo.onBoarding.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pushpopdemo.R
import com.example.pushpopdemo.databinding.RegistrationActivityBinding
import com.example.pushpopdemo.home.ui.activities.HomeActivity
import com.example.pushpopdemo.onBoarding.dataBase.RegisterDatabase
import com.example.pushpopdemo.onBoarding.model.User
import com.example.pushpopdemo.onBoarding.repository.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistrationActivity:AppCompatActivity(), View.OnClickListener {

  private lateinit var binding: RegistrationActivityBinding
    private val uiScope = CoroutineScope(Dispatchers.Main)
    val dao = RegisterDatabase.getInstance(application).userDao
    val repository = RegisterRepository(dao)


    companion object {
        val TAG: String = RegistrationActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            Intent(activity, RegistrationActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.run {
                activity.startActivity(this)

            }}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.registration_activity)



    }

    fun isValidEmail(email: String): Boolean {
        val emailMatcher = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$").matcher(email)
        return email.isNotEmpty() && emailMatcher.find()
    }

    fun validation(){
        if (binding.etEmailId!=null || binding.etPassword!=null){

            if (isValidEmail(binding.etEmailId.toString())){

                uiScope.launch {
                    if (binding.etEmailId!=null || binding.etPassword!=null){
                        uiScope.launch {

                            repository.insert(User(binding.etEmailId.toString(),0,binding.etPassword.toString()))
                        }
                    }
                    HomeActivity.startActivity(this@RegistrationActivity)

                }
            }
        }
    }

    override fun onClick(v: View?) {
        validation()
    }
}