package cat.copernic.letmedoit.General.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cat.copernic.letmedoit.databinding.ActivityHomeBinding

lateinit var binding : ActivityHomeBinding
class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}