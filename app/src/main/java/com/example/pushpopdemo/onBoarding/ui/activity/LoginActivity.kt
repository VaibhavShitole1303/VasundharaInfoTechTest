package com.example.pushpopdemo.onBoarding.ui.activity

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.pushpopdemo.R
import com.example.pushpopdemo.databinding.LoginActivityBinding
import com.example.pushpopdemo.home.ui.activities.HomeActivity
import com.example.pushpopdemo.onBoarding.dataBase.RegisterDatabase
import com.example.pushpopdemo.onBoarding.repository.RegisterRepository
import com.example.pushpopdemo.onBoarding.viewModel.LoginViewModel
import com.example.pushpopdemo.onBoarding.viewModel.LoginViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: LoginActivityBinding
    private lateinit var loginViewModel: LoginViewModel
    private val uiScope = CoroutineScope(Dispatchers.Main)
    val dao = RegisterDatabase.getInstance(application).userDao
    val repository = RegisterRepository(dao)

    val factory = LoginViewModelFactory(repository, application)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.login_activity)

        val application = requireNotNull(this).application

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this
    }

    override  fun onClick(v: View?) {
       when(v?.id){
           R.id.btn_login->{

               validation()
           }
           R.id.tv_register->{
               RegistrationActivity.startActivity(this)
           }
       }
    }

    fun isValidEmail(email: String): Boolean {
        val emailMatcher = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})\$").matcher(email)
        return email.isNotEmpty() && emailMatcher.find()
    }
    fun validation(){
        if (binding.etEmailId!=null || binding.etPassword!=null){

            if (isValidEmail(binding.etEmailId.toString())){

                uiScope.launch {

                    if (repository.getUserEmail(binding.etEmailId.toString())?.equals(binding.etEmailId.toString()) == true){
                        HomeActivity.startActivity(this@LoginActivity)
                    }
                    else{
                        Toast.makeText(this@LoginActivity,"this email id does not register",Toast.LENGTH_LONG).show()
                        RegistrationActivity.startActivity(this@LoginActivity)
                    }
                }
            }
            }
         }


}