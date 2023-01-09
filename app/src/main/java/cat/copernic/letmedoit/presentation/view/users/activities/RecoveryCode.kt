package cat.copernic.letmedoit.presentation.view.users.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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