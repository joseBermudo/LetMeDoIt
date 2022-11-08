package cat.copernic.letmedoit.Admin.view.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.UsersProvider
import cat.copernic.letmedoit.General.model.adapter.UsersAdapter
import cat.copernic.letmedoit.databinding.FragmentAdminBannedUsersBinding


class admin_banned_users : Fragment() {


    private var _binding:FragmentAdminBannedUsersBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBannedUsersBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView(){
        binding.recyclerViewBannedUsers.layoutManager = LinearLayoutManager(binding.root.context)
        Log.d("admin_banned_users", UsersProvider.obtenerUsers().toString())
        binding.recyclerViewBannedUsers.adapter = UsersAdapter(UsersProvider.obtenerUsers())
    }
}