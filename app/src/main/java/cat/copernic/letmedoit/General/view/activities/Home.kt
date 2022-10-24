package cat.copernic.letmedoit.General.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


        addFragment(HomeFragment(),R.id.frameLayout_manager)
        addFragment(Menu_Inferior(),R.id.frameLayout_menuInferiorPrincipal)
    }

    @Override
    override fun onBackPressed() {
    }
}