package cat.copernic.letmedoit.Users.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Visitante.view.activities.Login
import cat.copernic.letmedoit.databinding.ActivityRecoveryCodeBinding

class RecoveryCode : AppCompatActivity() {
    lateinit var binding: ActivityRecoveryCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoveryCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { onBackPressed() }
    }

}