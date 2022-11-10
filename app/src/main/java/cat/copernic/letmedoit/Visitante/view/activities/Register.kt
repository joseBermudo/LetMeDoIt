package cat.copernic.letmedoit.Visitante.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import cat.copernic.letmedoit.General.view.activities.Home
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.view.activities.RecoveryPassword_email
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.btnExitRegister.setOnClickListener { onBackPressed() }
        binding.txtSignIn.setOnClickListener { onBackPressed() }
        binding.btnSignIn.setOnClickListener() { checkLogin()  }

    }

    private fun checkLogin() {
        var email = binding.editEmail.text.toString().trim().lowercase()
        var password = binding.editPassword.text.toString()
        var confirmPassword = binding.editConfirmPassword.text.toString()

        if(email.isEmpty() or password.isEmpty() or confirmPassword.isEmpty()){
            Utils.showOkDialog("Please fill out Email and Password !!!",this)
            return
        }
        if(password != confirmPassword){
            Utils.showOkDialog("Passwords don't match !!!",this)
            return
        }

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(email).matches()){
            Utils.showOkDialog("Not a valid email !!!",this)
            return
        }

        register(email,password)

    }

    private fun register(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { response ->
            if (!response.isSuccessful){
                Utils.showOkDialog("Register failed :(",this)
                return@addOnCompleteListener
            }
            startActivity(Intent(this, Home::class.java))
            finish()
        }
    }

}