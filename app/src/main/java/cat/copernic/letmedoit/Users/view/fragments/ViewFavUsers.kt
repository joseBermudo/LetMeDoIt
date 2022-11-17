package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.provider.UsersProvider
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.model.adapter.FavUsersAdapter
import cat.copernic.letmedoit.databinding.FragmentViewFavUsersBinding


class viewFavUsers : Fragment() {

    private var _binding: FragmentViewFavUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewFavUsersBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }
    fun initRecyclerView(){
        binding.recyclerViewFavUser.layoutManager = GridLayoutManager(binding.root.context, 2)
        binding.recyclerViewFavUser.adapter = FavUsersAdapter(UsersProvider.obtenerUsers())
    }
}