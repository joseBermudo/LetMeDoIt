package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.UserFavoriteServices
import cat.copernic.letmedoit.data.model.*
import cat.copernic.letmedoit.databinding.FragmentVerListadoFavServicesBinding
import cat.copernic.letmedoit.presentation.adapter.general.ServiceAdapter
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerListadoFavServices.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class VerListadoFavServices : Fragment() {
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

    private lateinit var binding: FragmentVerListadoFavServicesBinding
    private var totalFavServices = 0
    private var obtainedFavServices = 0
    private var services = ArrayList<Service>()
    private val serviceViewModel : ServiceViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private lateinit var adapter : ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerListadoFavServicesBinding.inflate(inflater, container, false)
        //initRecyclerView()
        userViewModel.getFavoriteServices()
        initObservers()
        return binding.root

    }

    private fun initObservers() {
        userViewModel.getFavoriteServicesState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<ArrayList<UserFavoriteServices>> -> {
                    if(::adapter.isInitialized) adapter.clear()

                    totalFavServices = dataState.data.size
                    if (totalFavServices == 0) hideProgress()

                    dataState.data.forEach { serviceViewModel.getService(it.favorite_service_id) }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    hideProgress()
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        } )
        serviceViewModel.getServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    services.add(dataState.data)
                    obtainedFavServices++
                    if(obtainedFavServices == totalFavServices) initRecyclerView(services)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    hideProgress()
                }
                is DataState.Loading -> {
                }
                else -> Unit
            }
        } )
        userViewModel.deleteFavoriteServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {
                }
                else -> Unit
            }
        } )
        userViewModel.addFavoriteServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {
                }
                else -> Unit
            }
        } )
    }

    private fun hideProgress() {
        binding.loadingFavServices.isVisible = false
    }

    private fun showProgress() {
        binding.loadingFavServices.isVisible = true
    }
    fun initRecyclerView(services: ArrayList<Service>) {
        services.forEach { it.defaultFav = true }
        hideProgress()
        binding.recyclerFavServices.layoutManager = GridLayoutManager(binding.root.context, 2)
        adapter = ServiceAdapter(services,this,userViewModel,serviceViewModel)
        binding.recyclerFavServices.adapter = adapter
        obtainedFavServices = 0
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerListadoFavServices.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerListadoFavServices().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}