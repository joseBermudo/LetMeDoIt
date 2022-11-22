package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.presentation.adapter.users.DealsAdapter
import cat.copernic.letmedoit.data.provider.DealProvider

import cat.copernic.letmedoit.databinding.FragmentVerListadoDealsBinding




class verListadoDeals : Fragment() {

    private var _binding: FragmentVerListadoDealsBinding? = null
    private val binding get() = _binding!!
    lateinit var dealsRecyclerView: RecyclerView
    private lateinit var adapter: DealsAdapter

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

    fun initRecyclerView() {
        dealsRecyclerView = binding.recyclerViewListadoDeals
        binding.recyclerViewListadoDeals.layoutManager = LinearLayoutManager(binding.root.context)

        adapter = DealsAdapter(
            DealProvider.obtenerDeals()
        )

        dealsRecyclerView.adapter = adapter
    }

}