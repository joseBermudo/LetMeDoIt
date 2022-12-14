package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.Utils.datahepers.HistoryDeal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.presentation.adapter.users.DealsAdapter

import cat.copernic.letmedoit.databinding.FragmentVerListadoDealsBinding
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.DealViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class verListadoDeals : Fragment() {

    private var _binding: FragmentVerListadoDealsBinding? = null
    private val binding get() = _binding!!
    lateinit var dealsRecyclerView: RecyclerView
    private lateinit var adapter: DealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val userViewModel : UserViewModel by viewModels()
    private val dealViewModel : DealViewModel by viewModels()
    private val serviceViewModel : ServiceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        historyDealsIndex = 0
        historyDeals.clear()
        deals.clear()
        users.clear()
        services.clear()
        _binding = FragmentVerListadoDealsBinding.inflate(inflater, container, false)
        userViewModel.getHistoryDeals()
        initObservers()
        return binding.root
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun hideProgress(){
        binding.loadingDeals.isVisible = false
    }
    private fun showProgress(){
        binding.loadingDeals.isVisible = true
    }

    private var historyDeals = ArrayList<HistoryDeal>()
    private var deals = ArrayList<Deal>()
    private var users = ArrayList<Users>()
    private var services = ArrayList<Service>()

    private var historyDealsIndex = 0

    private fun initObservers() {
        userViewModel.getHistoryDealsState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<ArrayList<HistoryDeal>> -> {
                    historyDeals.addAll(dataState.data)
                    historyDeals.forEach { historyDeal ->
                        if (historyDeal.dealId.size == 0) hideProgress()
                        historyDeal.dealId.forEach {
                            dealViewModel.getDeal(it.deal_id)
                        }
                    }
                    if(historyDeals.size == 0 ) hideProgress()                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> { showProgress() }
                else -> Unit
            }
        } )
        dealViewModel.getDealState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Deal> -> {
                    deals.add(dataState.data)
                    if(historyDeals[historyDealsIndex].dealId.size == deals.size) deals.forEach {
                        historyDealsIndex++
                        if(it.users.userOneId == Constants.USER_LOGGED_IN_ID) userViewModel.getUser(it.users.userTwoId)
                        else userViewModel.getUser(it.users.userOneId)
                    }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
        userViewModel.getUserState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Users?> -> {
                    dataState.data?.let { users.add(it) }
                    if(users.size == deals.size) deals.forEach {
                        if(it.users.userOneId == Constants.USER_LOGGED_IN_ID) serviceViewModel.getService(it.services.serviceTwoId)
                        else serviceViewModel.getService(it.services.serviceOneId)
                    }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
        serviceViewModel.getServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    services.add(dataState.data)
                    if(services.size == deals.size) initView()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    fun initRecyclerView() {
        hideProgress()
        dealsRecyclerView = binding.recyclerViewListadoDeals
        binding.recyclerViewListadoDeals.layoutManager = LinearLayoutManager(binding.root.context)


        adapter = DealsAdapter(deals,users,services)

        dealsRecyclerView.adapter = adapter
    }

}