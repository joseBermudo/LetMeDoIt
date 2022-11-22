
package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.data.provider.UsersProvider
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import cat.copernic.letmedoit.databinding.FragmentAdminViewUsersBinding
import cat.copernic.letmedoit.presentation.adapter.general.UsersAdapter


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

        binding.btnFlechaBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    fun initRecyclerView(){
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewUsers.adapter = UsersAdapter(UsersProvider.obtenerUsers())
    }

}