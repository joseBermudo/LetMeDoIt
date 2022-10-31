package cat.copernic.letmedoit.Visitante.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cat.copernic.letmedoit.Admin.view.activities.MenuAdmin
import cat.copernic.letmedoit.General.view.activities.Home
import cat.copernic.letmedoit.Users.view.activities.RecoveryPassword_email
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class Login : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener{ checkLogin() }
        binding.btnSignUp.setOnClickListener { signUp() }
        binding.btnExitLogin.setOnClickListener { onBackPressed() }
        binding.btnForgotPassword.setOnClickListener { forgotPassword() }


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

        login(email,password)

    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { response ->
            if (!response.isSuccessful){
                Utils.showOkDialog("Login Failed: Wrong Credentials",this)
                return@addOnCompleteListener
            }
            //TODO: Implementar roles para ver si el usuario es admin y hacer la comprobación
            if(email == "alex@gmail.com"){
                startActivity(Intent(this, MenuAdmin::class.java))
                finish()
                return@addOnCompleteListener
            }
            startActivity(Intent(this,Home::class.java))
            finish()
        }
    }

}

