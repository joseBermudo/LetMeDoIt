package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.presentation.viewmodel.visitante.LoginViewModel
import cat.copernic.letmedoit.databinding.FragmentAdminMenuBinding
import cat.copernic.letmedoit.presentation.view.general.activities.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class admin_menu : Fragment() {

    private var _binding: FragmentAdminMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminMenuBinding.inflate(inflater, container, false)

        initObservers()
        binding.btnSingOff.setOnClickListener {
            loginViewModel.logOut()
        }

        binding.btnCategories.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_adminCategoriesList)
        }

        binding.btnUsers.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_view_users)
        }

        binding.btnReports.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_reports)
        }

        binding.btnArchivedReports.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_archived_reports)
        }

        return binding.root
    }

    private fun initObservers() {
        loginViewModel.logOutState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    loginViewModel.getUserData()
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


}