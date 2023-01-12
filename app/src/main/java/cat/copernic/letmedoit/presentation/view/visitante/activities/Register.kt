package cat.copernic.letmedoit.presentation.view.visitante.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.presentation.viewmodel.visitante.RegisterViewModel
import cat.copernic.letmedoit.databinding.ActivityRegisterBinding
import cat.copernic.letmedoit.presentation.view.general.activities.Home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

//Marca que la clase a fin de inyectarla.
@AndroidEntryPoint
class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        auth = Firebase.auth
        binding.btnExitRegister.setOnClickListener { onBackPressed() }
        binding.txtSignIn.setOnClickListener { onBackPressed() }
        binding.btnSignIn.setOnClickListener() { checkLogin() }

    }

    private val registerViewModel: RegisterViewModel by viewModels()

    private fun initObservers() {
        registerViewModel.registerState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Users> -> {
                    registerViewModel.saveUser(dataState.data)
                }
                is DataState.Error -> {
                    hideProgress()
                    Utils.showOkDialog(resources.getString(R.string.error), this, dataState.exception.message.toString(),this)
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        })

        registerViewModel.saveUserState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Boolean> -> {
                    startActivity(Intent(this, Home::class.java))
                    finish()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("${resources.getString(R.string.error)}", this, dataState.exception.message.toString(),this)
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        })
    }

    private fun hideProgress() {
        binding.btnSignIn.isEnabled = true
        binding.btnSignIn.text = resources.getString(R.string.txt_login_signIn)
        binding.registerLoading.isVisible = false
    }

    private fun showProgress() {
        binding.btnSignIn.isEnabled = false
        binding.btnSignIn.text = ""
        binding.registerLoading.isVisible = true
    }

    private fun checkLogin() {
        var email = binding.editEmail.text.toString().trim().lowercase()
        var password = binding.editPassword.text.toString()
        var confirmPassword = binding.editConfirmPassword.text.toString()
        val username = binding.editUsername.text.toString().trim()

        if (email.isEmpty() or password.isEmpty() or confirmPassword.isEmpty()) {
            Utils.showOkDialog(resources.getString(R.string.error),this,resources.getString(R.string.emailorPasswordEmpty), this)
            return
        }
        if (password != confirmPassword) {
            Utils.showOkDialog(resources.getString(R.string.error),this,resources.getString(R.string.passwordNotMatch), this)
            return
        }

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(email).matches()) {
            Utils.showOkDialog(resources.getString(R.string.error),this,resources.getString(R.string.notvalidemail), this)
            return
        }

        registerUser(email,password,username)

    }

    private fun registerUser(email: String, password: String, username: String) {
        registerViewModel.register(createUser(email, username), password)
    }

    private fun createUser(email: String, username: String): Users {
        return Users(
            email = email,
            rating = 0f,
            username = username
        )
    }

}