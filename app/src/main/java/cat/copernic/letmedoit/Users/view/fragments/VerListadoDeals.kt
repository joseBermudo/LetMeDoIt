package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.provider.UsersProvider

import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.view.model.adapter.ConversacionesAdapter
import cat.copernic.letmedoit.Users.view.model.adapter.DealsAdapter
import cat.copernic.letmedoit.databinding.FragmentVerListadoDealsBinding




class verListadoDeals : Fragment() {

    private var _binding: FragmentVerListadoDealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerListadoDealsBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    fun initRecyclerView(){
        binding.recyclerViewListadoDeals.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewListadoDeals.adapter = DealsAdapter(UsersProvider.obtenerUsers())
    }
}