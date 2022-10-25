package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.adapter.ServiceAdapter
import cat.copernic.letmedoit.General.model.ServiceProvider
import cat.copernic.letmedoit.General.viewmodel.SearchViewViewModel
import cat.copernic.letmedoit.databinding.FragmentHomeBinding
import cat.copernic.letmedoit.databinding.FragmentHomeServicesListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeServicesList.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    lateinit var binding : FragmentHomeServicesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeServicesListBinding.inflate(layoutInflater,container,false)
        inicializarRecyclerView()
        return binding.root
    }

    lateinit var serviceRecyclerView : RecyclerView
    lateinit var adapter : ServiceAdapter
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
        adapter = ServiceAdapter(ServiceProvider.getServices())
        serviceRecyclerView.adapter = adapter

    }

    lateinit var filterQuery : String

    /**
     * Recibimos el mensaje desde el fragmento del searchview utilizando el viewmodel del searchview
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity())[SearchViewViewModel::class.java]
        model.message.observe(viewLifecycleOwner, Observer {
            adapter.filter(it)
        })
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