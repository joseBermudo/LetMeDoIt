package cat.copernic.letmedoit.presentation.view.general.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.*
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ActivityHomeBinding
import cat.copernic.letmedoit.presentation.view.admin.activities.MenuAdmin
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.presentation.view.visitante.activities.UserBanned
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val userViewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Utils.showOkDialog("Error", this ,"Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Constants.TOKEN = token
            userViewModel.addDeviceToken(token)
        })

        val currentUser = FirebaseModule.firebaseAuthProvider().currentUser
        val bundle = intent.extras
        if (currentUser != null) {
            Constants.USER_LOGGED_IN_ID = currentUser.uid
            initObserver()
            userViewModel.getUser(Constants.USER_LOGGED_IN_ID)
        }
        else{
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_visitante)
        }

    }

    private fun initObserver() {
        userViewModel.getUserState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<Users?> -> {
                    val user = dataState.data
                    if (user != null) {
                        Constants.USER_LOGGED_IN = user
                    }
                    manageUserNavigation(user)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",this,dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    //Dependiendo de si el usuario es admin, visitante o usuario, se utiliza un NavController u Otro.
    private fun manageUserNavigation(currentUser : Users?){

        if(currentUser == null) {
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_visitante)
            return
        }

        //Usuario baneado
        if(currentUser.banned){
            startActivity(Intent(this,UserBanned::class.java))
            finish()
        }
        //Usuario admin
        if(currentUser.admin){
            startActivity(Intent(this, MenuAdmin::class.java))
            finish()
        }
        //Usuario logeado
        else{
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_leogeado)
        }
    }
    //Control para volver hacia atras en los recyclerviews, si el destino actual es la primera pantalla no vuelve hacia atras
    override fun onBackPressed() {
        val currentDestination = binding.navController.findNavController().currentDestination
        val sourceDestination = binding.navController.findNavController().previousBackStackEntry?.destination
        if (currentDestination != null) {
            if(sourceDestination!=null){
                if(sourceDestination.label == "fragment_home"){
                    if(currentDestination.label == "fragment_ver_conversaciones") return
                    if(currentDestination.label == "fragment_profiles_services_manager_vis") return
                    if(currentDestination.label == "fragment_new_service") return
                    if(currentDestination.label == "fragment_opciones_de_cuenta") return
                }
                if(currentDestination.label == "fragment_conclude_deal" && sourceDestination.label == "fragment_ver_conversaciones") findNavController(R.id.navController).navigate(R.id.verConversaciones)
                if(currentDestination.label == "fragment_conclude_deal" && sourceDestination.label == "fragment_ver_deal") findNavController(R.id.navController).navigate(R.id.verConversaciones)
                if(currentDestination.label == "fragment_ver_conversaciones" && sourceDestination.label == "fragment_ver_deal") return
                if(currentDestination.label == "fragment_ver_conversaciones" && sourceDestination.label == "fragment_conclude_deal") return
            }

            if(currentDestination.label == "fragment_rate_user") findNavController(R.id.navController).navigate(R.id.verConversaciones)
            if(currentDestination.label == "fragment_conclude_deal") findNavController(R.id.navController).navigate(R.id.verConversaciones)
            if(currentDestination.label == "fragment_rate_user") findNavController(R.id.navController).navigate(R.id.verConversaciones)
            if(currentDestination.label == "fragment_ver_deal") findNavController(R.id.navController).navigate(R.id.verConversaciones)
            else if ((currentDestination.id
                    ?: -1) != R.id.homeFragment
            ) findNavController(R.id.navController).popBackStack()
        }

    }
}