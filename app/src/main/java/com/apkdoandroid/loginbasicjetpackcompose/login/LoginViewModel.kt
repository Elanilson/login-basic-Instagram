package com.apkdoandroid.loginbasicjetpackcompose.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _Email = MutableLiveData<String>()
    var email : LiveData<String> = _Email

    private val _PassWord = MutableLiveData<String>()
    var passWord : LiveData<String> = _PassWord

    private val _IsLoginEnable = MutableLiveData<Boolean>()
    var isLoginEnable : LiveData<Boolean> = _IsLoginEnable



    fun onLoginChange(emailL: String, password: String) {
        _Email.value = emailL
        _PassWord.value = password
       _IsLoginEnable.value = enableLogin(emailL,password)
    }

    fun enableLogin(email : String , password : String) =  Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6


}
