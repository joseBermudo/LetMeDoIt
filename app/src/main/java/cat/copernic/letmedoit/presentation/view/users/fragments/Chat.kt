package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.FragmentChatBinding
import cat.copernic.letmedoit.presentation.view.general.fragments.viewServiceDirections
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.firebase.firestore.auth.User
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chat.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class chat : Fragment() {

    lateinit var binding : FragmentChatBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentChatBinding.inflate(inflater, container, false)
        initView()
        initListeners()
        initObservers()
        return binding.root
    }

    private val args : chatArgs by navArgs()
    private val userViewModel : UserViewModel by viewModels()
    private  var users =  ArrayList<Users>()

    private fun initView() {
        userViewModel.getUser(Constants.USER_LOGGED_IN_ID)
    }

    private fun initObservers() {
        userViewModel.getUserState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Users?> -> {
                    if(dataState.data != null) users.add(dataState.data)
                    if(users.size == 1 ) userViewModel.getUser(args.idUser)
                    binding.btnCreateDeal.isEnabled = true

                    if(users.size > 1 && users[1].banned){
                        binding.txtTitleChat.text = "User has been banned"
                        binding.btnCreateDeal.isEnabled = false
                    }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    binding.btnCreateDeal.isEnabled = true
                }
                is DataState.Loading -> { binding.btnCreateDeal.isEnabled = false }
                else -> Unit
            }
        } )
    }

    private fun initListeners() {
        binding.btnArrowBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.btnCreateDeal.setOnClickListener { goToCreateDeal() }
    }

    private fun goToCreateDeal() {
        val action = chatDirections.chatToCreateDeal(users[0],users[1])
        Navigation.findNavController(requireView()).navigate(action)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chat.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            chat().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}