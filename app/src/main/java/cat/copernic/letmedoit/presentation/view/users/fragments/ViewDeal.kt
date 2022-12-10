package cat.copernic.letmedoit.presentation.view.users.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.HistoryDeal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.databinding.FragmentVerDealBinding
import cat.copernic.letmedoit.presentation.view.general.fragments.viewServiceDirections
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewDeal : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private val serviceViewModel : ServiceViewModel by viewModels()
    private val args : ViewDealArgs by navArgs()

    lateinit var binding: FragmentVerDealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerDealBinding.inflate(inflater,container,false)

        services.clear()
        serviceViewModel.getService(deal.services.serviceOneId)
        initListeners()
        initObservers()
        return binding.root
    }

    private val user by lazy {
        args.user
    }
    private val deal by lazy {
        args.deal
    }
    private val services = ArrayList<Service>()
    private fun initObservers() {
        serviceViewModel.getServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    services.add(dataState.data)
                    if(services.size == 1) serviceViewModel.getService(deal.services.serviceTwoId)

                    if(services.size == 2) initView()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    private lateinit var myService: Service
    private lateinit var  hisService: Service
    private fun initListeners() {
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.seeMyService.setOnClickListener { goToViewService(myService) }
        binding.seeHisService.setOnClickListener { goToViewService(hisService) }
    }

    private fun goToViewService(service: Service) {
        val action  = ViewDealDirections.actionViewDealToViewService(service)
        requireView().findNavController().navigate(action)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        if(deal.users.userOneId == Constants.USER_LOGGED_IN_ID){
            myService = services[1]
            hisService = services[0]
        }
        else{
            myService = services[0]
            hisService = services[1]
        }

        Picasso.get().load(user.avatar).into(binding.iconUser)
        binding.txtProgressDeal.text = if (!deal.accepted)  "1/2" else "2/2"
        if(Constants.USER_LOGGED_IN_ID == deal.users.userOneId) binding.btnAccept.isEnabled = false
        binding.nameSurname.text = "${user.name} ${user.surname} \n @${user.username}"
        binding.myServiceSubText.text = services[0].title
        binding.hisServiceSubText.text = services[1].title
        binding.dealDescription.setText(deal.description)
    }

}