package cat.copernic.letmedoit.Users.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import cat.copernic.letmedoit.databinding.ActivityRecoveryPasswordEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RecoveryPassword_email : AppCompatActivity() {
    lateinit var binding: ActivityRecoveryPasswordEmailBinding
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryPasswordEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.btnGetRecoveryCode.setOnClickListener{ checkEmail() }
        binding.btnBack.setOnClickListener { onBackPressed() }
    }

    private fun checkEmail() {
        val email = binding.editEmail.text.toString().trim().lowercase()
        if(email.isEmpty()){
            Toast.makeText(this,"Please fill out Email !!!",
                Toast.LENGTH_LONG).show()
            return
        }
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        if (!pattern.matcher(email).matches()){
            Toast.makeText(this,"Not a valid email !!!",
                Toast.LENGTH_LONG).show()
            return
        }

        sendRecoveryCode(email)
    }

    private fun sendRecoveryCode(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener{ response ->
            if (!response.isSuccessful){
                Toast.makeText(this,"Recovery failed :(",
                    Toast.LENGTH_LONG).show()
                return@addOnCompleteListener
            }
            startActivity(Intent(this, RecoveryCode::class.java))
            finish()
        }
    }


}