package cat.copernic.letmedoit.Admin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ActivityMenuAdminBinding

class MenuAdmin : AppCompatActivity() {
    lateinit var binding : ActivityMenuAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onBackPressed() {
        if ((binding.navController.findNavController().currentDestination?.id
                ?: -1) != R.id.admin_menu
        )
            findNavController(R.id.navController).popBackStack()
    }
}