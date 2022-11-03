package cat.copernic.letmedoit.Admin.model.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.Admin.model.UsersProvider
import cat.copernic.letmedoit.Admin.model.adapter.UsersAdapter
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminBannedUsersBinding
import cat.copernic.letmedoit.databinding.FragmentAdminViewUsersBinding


class admin_view_users : Fragment() {

    private var _binding:FragmentAdminViewUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminViewUsersBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView(){
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewUsers.adapter = UsersAdapter(UsersProvider.obtenerUsers())
    }

}