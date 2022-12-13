package cat.copernic.letmedoit.presentation.view.general.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ActivityHomeBinding
import cat.copernic.letmedoit.presentation.view.admin.activities.MenuAdmin
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

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

        if (currentUser != null) {
            Constants.USER_LOGGED_IN_ID = currentUser.uid
            initObserver()
            userViewModel.getUser(Constants.USER_LOGGED_IN_ID)
        }
        //No hay Usuario logeado
        if(currentUser==null){
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_visitante)
        }
        else if(currentUser.email == "alex@gmail.com"){
            startActivity(Intent(this, MenuAdmin::class.java))
            finish()
        }
        //Usuario logeado
        else{
            binding.navController.getFragment<Fragment>().findNavController().setGraph(R.navigation.app_navigation_leogeado)
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
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",this,dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    //Control para volver hacia atras en los recyclerviews, si el destino actual es la primera pantalla no vuelve hacia atras
    override fun onBackPressed() {
        val currentDestination = binding.navController.findNavController().currentDestination
        if (currentDestination != null) {
            if(currentDestination.label == "fragment_rate_user"){
                findNavController(R.id.navController).navigate(R.id.verConversaciones)
            }

            else if ((currentDestination.id
                    ?: -1) != R.id.homeFragment
            ) findNavController(R.id.navController).popBackStack()
        }

    }
}