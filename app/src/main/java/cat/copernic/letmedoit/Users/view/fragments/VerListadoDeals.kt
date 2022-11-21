package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.General.model.provider.UsersProvider
import cat.copernic.letmedoit.General.view.fragments.chats_deals_manager_visDirections
import cat.copernic.letmedoit.Users.view.fragments.verListadoDealsDirections.Companion.listadoDealsToConcludeDeal
import cat.copernic.letmedoit.Users.view.model.adapter.DealsAdapter
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
            UsersProvider.obtenerUsers(),
            onClickRecyclerDeals = {users -> onClickItem(users)})
    }

    private fun onClickItem(users: Users) {
        val action = verListadoDealsDirections.listadoDealsToConcludeDeal()
        findNavController().navigate(action)
    }
}