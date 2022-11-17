package cat.copernic.letmedoit.Users.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cat.copernic.letmedoit.General.view.activities.Home

import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.Visitante.viewmodel.LoginViewModel
import cat.copernic.letmedoit.databinding.FragmentOpcionesDeCuentaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.view.LenguagesProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountOptions.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AccountOptions : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val loginViewModel : LoginViewModel by viewModels()
    private lateinit var binding :FragmentOpcionesDeCuentaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpcionesDeCuentaBinding.inflate(inflater,container,false)

        initObservers()
        val languagesString = ArrayList<String>()
        LenguagesProvider.obtenerLenguages().map { x -> x.lenguage }.toCollection(languagesString)
        Utils.AsignarPopUpSpinnerLenguages(requireContext(), languagesString, binding.spinnerLenguages)

        auth = Firebase.auth
        binding.btnUserProfile.setOnClickListener{ Utils.goToDestination(requireView(), R.id.perfilUsuarioMenuSuperior)}
        binding.btnEditProfile.setOnClickListener{ Utils.goToDestination(requireView(), R.id.editarInformacionPerfil)}
        binding.btnSignOut.setOnClickListener{ loginViewModel.logOut() }

        return binding.root
    }

    private fun initObservers() {
        loginViewModel.logOutState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    loginViewModel
                    startActivity(Intent(requireActivity(), Home::class.java))
                    requireActivity().finish()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    /*private fun singOut() {
        auth.signOut()
        startActivity(Intent(requireActivity(), Home::class.java))
        requireActivity().finish()
    }*/
}