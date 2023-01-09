package cat.copernic.letmedoit.presentation.view.general.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.UserConstants
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.UserFavoriteServices
import cat.copernic.letmedoit.databinding.FragmentHomeServicesListBinding
import cat.copernic.letmedoit.presentation.adapter.general.ServiceAdapter
import cat.copernic.letmedoit.presentation.viewmodel.general.SearchViewViewModel
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeServicesList.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeServicesList : Fragment() {
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


    lateinit var binding: FragmentHomeServicesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeServicesListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    /**
     * Recibimos el mensaje desde el fragmento del searchview utilizando el viewmodel del searchview
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = parentFragmentManager.findFragmentById(R.id.fragment_searchview)
        iconFilter = searchView?.view?.findViewById(R.id.iconFilter)
        iconClearFilter = searchView?.view?.findViewById(R.id.clearFilters)
        if (iconFilter != null) {
            iconFilter!!.isEnabled = false
            iconFilter!!.background = ResourcesCompat.getDrawable(resources,R.drawable.icon_filter_disabled,null)
        }
        initObserver()
        userViewModel.getFavoriteServices()
        val model = ViewModelProvider(requireActivity())[SearchViewViewModel::class.java]
        model.message.observe(viewLifecycleOwner, Observer {
            if (::adapter.isInitialized)
                adapter.filter(it)
        })
    }

    private var totalFavServices = 0
    private var obtainedFavServices = 0
    private lateinit var services: List<Service>
    private var searchView: Fragment? = null
    private var iconFilter: TextView? = null
    private var iconClearFilter: TextView? = null
    private fun initObserver() {
        serviceViewModel.getServicesState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Service>> -> {
                    dataState.data.forEach {
                        if (UserConstants.USER_FAVORITE_SERVICES_IDS.contains(it.id)) it.defaultFav = true
                    }
                    services = dataState.data
                    inicializarRecyclerView()
                    hideProgress()
                }
                is DataState.Error -> {
                    Utils.showOkDialog(
                        "Error: ",
                        requireContext(),
                        dataState.exception.message.toString()
                    )
                    hideProgress()
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        })
        userViewModel.getFavoriteServicesState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<ArrayList<UserFavoriteServices>> -> {
                    UserConstants.USER_FAVORITE_SERVICES_IDS.clear()
                    dataState.data.forEach { UserConstants.USER_FAVORITE_SERVICES_IDS.add(it.favorite_service_id) }
                }
                is DataState.Error -> {
                    Utils.showOkDialog(
                        "Error: ",
                        requireContext(),
                        dataState.exception.message.toString()
                    )
                }
                is DataState.Loading -> {}
                else -> serviceViewModel.getAllServices()
            }
        })
    }

    private fun hideProgress() {
        binding.loadingServices.isVisible = false
    }

    private fun showProgress() {
        binding.loadingServices.isVisible = true
    }

    private val userViewModel: UserViewModel by viewModels()

    private val serviceViewModel: ServiceViewModel by viewModels()

    lateinit var serviceRecyclerView: RecyclerView
    lateinit var adapter: ServiceAdapter

    /**
     * Inicializa el RecyclerView
     * */
    private fun inicializarRecyclerView() {
        serviceRecyclerView = binding.serviceRecyclerView
        //LinearLayoutManager HORIZONTAL
        //serviceRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL,false)
        serviceRecyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        //Asignación del adaptador al recyclerview.

        serviceRecyclerView.setHasFixedSize(true)

        adapter = ServiceAdapter(ArrayList(services), this, userViewModel, serviceViewModel)
        serviceRecyclerView.adapter = adapter

        if (iconFilter != null) {
            iconFilter!!.isEnabled = true
            iconFilter!!.background = ResourcesCompat.getDrawable(resources,R.drawable.filter_icon,null)
        }
        val args = arguments
        val sortingType = args?.getInt("sortingType")
        val category = args?.getParcelable<Category>("category")
        if (sortingType != null) {
            iconClearFilter?.setOnClickListener {
                adapter.clearFilters()
                if (iconFilter != null) {
                    iconFilter!!.visibility = View.VISIBLE
                    iconClearFilter!!.visibility = View.GONE
                }
            }
            if (sortingType >= 0) {
                when (sortingType) {
                    0 -> {
                        adapter.orderByName(category)
                    }
                    1 -> {
                        adapter.orderByNewestDate(category)
                    }
                    2 -> {
                        adapter.orderByOldestDate(category)
                    }
                    3 -> {
                        adapter.sortByCategory(category)
                    }
                }
                if (iconFilter != null && iconClearFilter != null) {
                    iconFilter!!.visibility = View.GONE
                    iconClearFilter!!.visibility = View.VISIBLE
                }
            }
            else{
                if (iconFilter != null && iconClearFilter != null) {
                    iconFilter!!.visibility = View.VISIBLE
                    iconClearFilter!!.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home_services_list.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeServicesList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}