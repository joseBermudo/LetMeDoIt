package cat.copernic.letmedoit.presentation.view.users.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Opinion
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.databinding.FragmentRateUserBinding
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RateUser.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RateUser : Fragment() {
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

    private val user by lazy {
        args.user
    }
    private val userViewModel : UserViewModel by viewModels()
    private val args : RateUserArgs by navArgs()
    lateinit var binding : FragmentRateUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRateUserBinding.inflate(inflater,container,false)
        initView()
        initObservers()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.saveBtn.setOnClickListener{ addOpinion() }
        binding.btnBack.setOnClickListener{ requireActivity().onBackPressed()}
    }

    private fun addOpinion() {
        val opinion = Opinion(
            userId = Constants.USER_LOGGED_IN_ID,
            rating = binding.ratingBar.rating,
            description = binding.editOpinionInput.text.toString(),
            deal_id = args.dealId,
            serviceId = args.serviceId
        )
        userViewModel.addOpinion(opinion,user.id)
    }

    private fun initObservers() {
        userViewModel.addOpinionState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    hideProgress()

                    val action = RateUserDirections.rateUserToHome()
                    Navigation.findNavController(requireView()).navigate(action)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    hideProgress()
                }
                is DataState.Loading -> { showProgress() }
                else -> Unit
            }
        } )
    }

    private fun hideProgress(){
        binding.saveLoading.isVisible = false
        binding.saveBtn.isVisible = true
        binding.saveBtn.isEnabled = true
    }
    private fun showProgress(){
        binding.saveBtn.isVisible = false
        binding.saveBtn.isEnabled = false
        binding.saveLoading.isVisible = true
    }
    @SuppressLint("SetTextI18n")
    private fun initView() {
        if(user.avatar != "") Picasso.get().load(user.avatar).into(binding.iconUser)
        binding.user.text = "${user.name} ${user.surname} \n @${user.username}"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment rate_user.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RateUser().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}