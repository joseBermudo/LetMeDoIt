package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.ServiceProvider
import cat.copernic.letmedoit.General.model.adapter.ServiceAdapter
import cat.copernic.letmedoit.General.viewmodel.SearchViewViewModel
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentPerfilUsuarioServiciosBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilUsuario.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilUsuarioServicios : Fragment() {
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

    //RecyclerView
    lateinit var serviceRecyclerView : RecyclerView
    lateinit var adapter : ServiceAdapter
    lateinit var binding : FragmentPerfilUsuarioServiciosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPerfilUsuarioServiciosBinding.inflate(inflater,container,false)
        serviceRecyclerView = binding.recylerViewServices
        serviceRecyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        //Asignación del adaptador al recyclerview.

        serviceRecyclerView.setHasFixedSize(true)
        adapter = ServiceAdapter(ServiceProvider.getServices())
        serviceRecyclerView.adapter = adapter

        val model = ViewModelProvider(requireActivity())[SearchViewViewModel::class.java]
        model.message.observe(viewLifecycleOwner, Observer {
            adapter.filter(it)
        })

        return binding.root
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
        fun newInstance(param1: String, param2: String) =
            PerfilUsuarioServicios().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}