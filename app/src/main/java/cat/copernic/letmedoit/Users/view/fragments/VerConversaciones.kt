package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.provider.UsersProvider
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.databinding.FragmentVerConversacionesBinding
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.General.view.fragments.chats_deals_manager_visDirections
import androidx.navigation.fragment.findNavController
import cat.copernic.letmedoit.Users.model.adapter.ConversacionesAdapter

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


    fun initRecyclerView(){
        conversacionesRecyclerView = binding.recyclerViewConversaciones
        binding.recyclerViewConversaciones.layoutManager = LinearLayoutManager(binding.root.context)

        adapter = ConversacionesAdapter(
            UsersProvider.obtenerUsers(),
            onClickRecyclerV = {users -> onClickItem(users)})
        conversacionesRecyclerView.adapter = adapter

        }
    private fun onClickItem(users: Users) {
        val action = chats_deals_manager_visDirections.conversacionesToChat()
        findNavController().navigate(action)
    }
}