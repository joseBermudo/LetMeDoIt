package cat.copernic.letmedoit.Users.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ActivityRecoveryPasswordEmailBinding

class RecoveryPassword_email : AppCompatActivity() {
    lateinit var binding: ActivityRecoveryPasswordEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery_password_email)
    }
}