package cat.copernic.letmedoit.General.view.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth


class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentUser = FirebaseAuth.getInstance().currentUser
        //No hay Usuario logeado
        if(currentUser==null){
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_visitante)
        }
        //Usuario logeado
        else{
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_leogeado)
        }

    }

    //Control para volver hacia atras en los recyclerviews, si el destino actual es la primera pantalla no vuelve hacia atras
    override fun onBackPressed() {
        if ((binding.navController.findNavController().currentDestination?.id
                ?: -1) != R.id.homeFragment
        )
            findNavController(R.id.navController).popBackStack()
    }
}