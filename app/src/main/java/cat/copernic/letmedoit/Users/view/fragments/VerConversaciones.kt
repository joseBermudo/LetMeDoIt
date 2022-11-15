package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.UsersProvider
import cat.copernic.letmedoit.General.model.adapter.UsersAdapter
import cat.copernic.letmedoit.Users.view.model.adapter.ConversacionesAdapter
import cat.copernic.letmedoit.databinding.FragmentVerConversacionesBinding


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
        binding.recyclerViewConversaciones.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewConversaciones.adapter = ConversacionesAdapter(UsersProvider.obtenerUsers())
    }
}