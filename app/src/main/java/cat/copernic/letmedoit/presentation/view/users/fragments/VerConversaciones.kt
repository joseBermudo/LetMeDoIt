package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.data.provider.UsersProvider
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.databinding.FragmentVerConversacionesBinding
import cat.copernic.letmedoit.data.model.Users
import androidx.navigation.fragment.findNavController
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.presentation.adapter.users.ConversacionesAdapter
import cat.copernic.letmedoit.presentation.view.general.fragments.chats_deals_manager_visDirections

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [verConversaciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class verConversaciones : Fragment() {

    private var _binding: FragmentVerConversacionesBinding? = null
    private val binding get() = _binding!!
    lateinit var conversacionesRecyclerView : RecyclerView
    private lateinit var adapter: ConversacionesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerConversacionesBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun initRecyclerView(){
        conversacionesRecyclerView = binding.recyclerViewConversaciones
        binding.recyclerViewConversaciones.layoutManager = LinearLayoutManager(binding.root.context)

        adapter = ConversacionesAdapter(
            UsersProvider.obtenerUsers(),
            onClickRecyclerV = { users -> onClickItem(users) })
        conversacionesRecyclerView.adapter = adapter

        }
    private fun onClickItem(users: Users) {
        val action = chats_deals_manager_visDirections.conversacionesToChat(users.id.toString())
        findNavController().navigate(action)
    }
}