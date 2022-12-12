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
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.databinding.FragmentVerDealBinding
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.DealViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewDeal : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private val userViewModel : UserViewModel by viewModels()
    private val serviceViewModel : ServiceViewModel by viewModels()
    private val args : ViewDealArgs by navArgs()
    private val dealViewModel : DealViewModel by viewModels()

    lateinit var binding: FragmentVerDealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerDealBinding.inflate(inflater,container,false)

        if (deal.accepted) goToConcludeDeal()

        dealViewModel.suscribeForUpdates(deal.id)
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
        dealViewModel.suscribeForUpdatesState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Deal?> -> {
                    val deal = dataState.data
                    if (deal != null) {
                        if(deal.accepted) goToConcludeDeal()
                    }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
        dealViewModel.acceptState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    deal.accepted = true
                    if(deal.accepted) goToConcludeDeal()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
        dealViewModel.denyState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    userViewModel.deleteDealFromHistory(deal.id,Constants.USER_LOGGED_IN_ID)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
        userViewModel.deleteDealFromHistoryState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                    if (firstDelete) {
                        userViewModel.deleteDealFromHistory(deal.id,user.id)
                        firstDelete = false
                    }
                    else Utils.goToDestination(requireView(), R.id.verListadoDeals)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }
    private var firstDelete = true
    private fun goToConcludeDeal() {
        val action  = ViewDealDirections.verDealToConcludeDeal(deal,user)
        requireView().findNavController().navigate(action)
    }

    private lateinit var myService: Service
    private lateinit var  hisService: Service
    private fun initListeners() {
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }

        binding.seeMyService.setOnClickListener { goToViewService(myService) }
        binding.seeHisService.setOnClickListener { goToViewService(hisService) }
        binding.btnAccept.setOnClickListener { acceptDeal() }
        binding.btnDeny.setOnClickListener { denyDeal() }
    }

    private fun acceptDeal() {
        dealViewModel.accept(deal.id)
    }

    private fun denyDeal() {
        dealViewModel.deny(deal.id)
    }

    private fun goToViewService(service: Service) {
        val action  = ViewDealDirections.actionViewDealToViewService(service)
        requireView().findNavController().navigate(action)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        if(deal.users.userOneId == Constants.USER_LOGGED_IN_ID){
            myService = services[0]
            hisService = services[1]
        }
        else{
            myService = services[1]
            hisService = services[0]
        }

        Picasso.get().load(user.avatar).into(binding.iconUser)
        binding.txtProgressDeal.text = if (!deal.accepted)  "1/2" else "2/2"
        if(Constants.USER_LOGGED_IN_ID == deal.users.userOneId) binding.btnAccept.isEnabled = false
        binding.nameSurname.text = "${user.name} ${user.surname} \n @${user.username}"
        binding.myServiceSubText.text = myService.title
        binding.hisServiceSubText.text = hisService.title
        binding.dealDescription.setText(deal.description)
    }

}