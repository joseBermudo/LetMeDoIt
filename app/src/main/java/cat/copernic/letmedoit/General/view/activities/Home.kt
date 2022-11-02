package cat.copernic.letmedoit.General.view.activities

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import cat.copernic.letmedoit.General.view.fragments.HomeCategoriesList
import cat.copernic.letmedoit.General.view.fragments.HomeFragment
import cat.copernic.letmedoit.General.view.fragments.Menu_Inferior
import cat.copernic.letmedoit.General.view.fragments.SearchView

import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Utils.Companion.addFragment
import cat.copernic.letmedoit.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    //Control para volver hacia atras en los recyclerviews, si el destino actual es la primera pantalla no vuelve hacia atras
    override fun onBackPressed() {
        if ((binding.navController.findNavController().currentDestination?.id
                ?: -1) != R.id.homeFragment
        )
            findNavController(R.id.navController).popBackStack()
    }
}