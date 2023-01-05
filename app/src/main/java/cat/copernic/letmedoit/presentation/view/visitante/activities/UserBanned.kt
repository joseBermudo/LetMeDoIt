package cat.copernic.letmedoit.presentation.view.visitante.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ActivityLoginBinding
import cat.copernic.letmedoit.databinding.ActivityUserBannedBinding


class UserBanned : AppCompatActivity() {

    lateinit var binding: ActivityUserBannedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBannedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailIcon.setOnClickListener { sendEmail() }
    }

    private fun sendEmail(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:contact@letmedoit.com") // only email apps should handle this

        startActivity(
            Intent.createChooser(intent,
            "Send Email Using: "))
    }
    override fun onBackPressed() {
        return
    }
}