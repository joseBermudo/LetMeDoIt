package cat.copernic.letmedoit.presentation.view.general.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.UserServices
import cat.copernic.letmedoit.databinding.FragmentPerfilUsuarioServiciosBinding
import cat.copernic.letmedoit.presentation.adapter.general.ServiceAdapter
import cat.copernic.letmedoit.presentation.viewmodel.general.SearchViewViewModel
import cat.copernic.letmedoit.presentation.viewmodel.general.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilUsuario.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PerfilUsuarioServicios(private val servicesId: ArrayList<UserServices>?) : Fragment() {
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

    private val serviceViewModel : ServiceViewModel by viewModels()
    private var services = ArrayList<Service>()
    private lateinit var serviceRecyclerView : RecyclerView

    lateinit var adapter : ServiceAdapter
    lateinit var binding : FragmentPerfilUsuarioServiciosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initObservers()
        servicesId?.forEach { serviceViewModel.getService(it.service_id) }

        // Inflate the layout for this fragment
        binding = FragmentPerfilUsuarioServiciosBinding.inflate(inflater,container,false)
        serviceRecyclerView = binding.recylerViewServices
        serviceRecyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        //Asignación del adaptador al recyclerview.

        serviceRecyclerView.setHasFixedSize(true)

        val model = ViewModelProvider(requireActivity())[SearchViewViewModel::class.java]
        model.message.observe(viewLifecycleOwner, Observer {
            adapter.filter(it)
        })

        return binding.root
    }

    private fun initObservers() {
        serviceViewModel.getServiceState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Service> -> {
                    services.add(dataState.data)
                    if(services.size == servicesId!!.size) showServices()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {  }
                else -> Unit
            }
        } )
    }

    private fun showServices() {
        adapter = ServiceAdapter(services)
        serviceRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilUsuario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String): PerfilUsuarioServicios {
            val servicesId = ArrayList<UserServices>()
            return PerfilUsuarioServicios(servicesId = servicesId).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        }
    }
}