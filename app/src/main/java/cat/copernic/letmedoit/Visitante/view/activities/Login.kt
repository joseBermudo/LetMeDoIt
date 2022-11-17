package cat.copernic.letmedoit.Visitante.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import cat.copernic.letmedoit.Admin.view.activities.MenuAdmin

import cat.copernic.letmedoit.General.view.activities.Home
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.view.activities.RecoveryPassword_email
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.Utils.di.FirebaseModule
import cat.copernic.letmedoit.Visitante.viewmodel.LoginViewModel
import cat.copernic.letmedoit.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

//Marca que la clase a fin de inyectarla.
@AndroidEntryPoint
class Login : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private val loginViewModel : LoginViewModel by viewModels()
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        auth = Firebase.auth
        binding.btnSignIn.setOnClickListener{ checkLogin() }
        binding.btnSignUp.setOnClickListener { signUp() }
        binding.btnExitLogin.setOnClickListener { onBackPressed() }
        binding.btnForgotPassword.setOnClickListener { forgotPassword() }


    }

    private var activityChanged : Boolean = false
    private fun initObservers() {
        loginViewModel.loginState.observe(this,Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    activityChanged = false
                    loginViewModel.getUserData()
                }
                is DataState.Error -> {
                    hideProgress()
                    Utils.showOkDialog("Error: ",this,dataState.exception.message.toString())
                }
                is DataState.Loading -> { showProgress() }
                else -> Unit
            }
        } )

        loginViewModel.userDataState.observe(this,Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    if (activityChanged)
                        return@Observer
                    activityChanged = true
                    loginViewModel.getUserData()
                    startActivity(Intent(this,Home::class.java))
                    finish()

                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",this,dataState.exception.message.toString())
                }
                is DataState.Loading -> { showProgress() }
                else -> Unit
            }
        } )
    }

    private fun hideProgress() {
        binding.btnSignIn.isEnabled = true
        binding.btnSignIn.text = resources.getString(R.string.txt_login_signIn)
        binding.loginLoading.isVisible = false
    }

    private fun showProgress() {
        binding.btnSignIn.isEnabled = false
        binding.btnSignIn.text = ""
        binding.loginLoading.isVisible = true
    }

    private fun forgotPassword() {
        startActivity(Intent(this,RecoveryPassword_email::class.java))
    }

    private fun signUp() {
        startActivity(Intent(this,Register::class.java))
    }

    private fun checkLogin() {
        var email = binding.editUsername.text.toString().trim().lowercase()
        var password = binding.editPassword.text.toString()

        if(email.isEmpty() or password.isEmpty()){
            Utils.showOkDialog("Please fill out Email and Password !!!", this)
            return
        }

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(email).matches()){
            Utils.showOkDialog("Not a Valid Email !!!",this)
            return
        }

        loginUser(email,password)
    }

    private fun loginUser(email : String, password: String) {
        loginViewModel.login(email,password)
    }

}

