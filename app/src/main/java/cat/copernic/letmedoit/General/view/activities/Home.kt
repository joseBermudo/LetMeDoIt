package cat.copernic.letmedoit.General.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cat.copernic.letmedoit.General.view.fragments.HomeCategoriesList
import cat.copernic.letmedoit.General.view.fragments.SearchView
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Utils.Companion.addFragment
import cat.copernic.letmedoit.databinding.ActivityHomeBinding

lateinit var binding : ActivityHomeBinding
class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        addFragment(SearchView(),R.id.frameLayout_searchView)
        addFragment(HomeCategoriesList(),R.id.frameLayout_categories)

    }

    @Override
    override fun onBackPressed() {
    }
}