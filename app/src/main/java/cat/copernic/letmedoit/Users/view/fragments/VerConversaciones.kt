package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Admin.model.adapter.AdminCategoryAdapter
import cat.copernic.letmedoit.General.model.adapter.UsersAdapter
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.General.model.provider.CategoryProvider
import cat.copernic.letmedoit.General.model.provider.UsersProvider
import cat.copernic.letmedoit.General.view.fragments.chats_deals_manager_visDirections
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.model.data.Chat
import cat.copernic.letmedoit.Users.view.model.adapter.ConversacionesAdapter
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentVerConversacionesBinding
import com.google.firebase.firestore.auth.User


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