package cat.copernic.letmedoit.Visitante.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}