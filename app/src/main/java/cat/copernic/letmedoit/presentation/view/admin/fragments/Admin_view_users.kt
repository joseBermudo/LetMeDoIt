
package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.data.provider.UsersProvider
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import cat.copernic.letmedoit.databinding.FragmentAdminViewUsersBinding
import cat.copernic.letmedoit.presentation.adapter.general.UsersAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class admin_view_users : Fragment() {

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.rotate_open_animation
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.rotate_close_animation
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.from_bottom_animation
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.to_bottom_animation
        )
    }

    private var open: Boolean = false

    private var _binding:FragmentAdminViewUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var btnOpenMenu: FloatingActionButton
    private lateinit var btnDelete: FloatingActionButton
    private lateinit var btnNewUser: FloatingActionButton
    private lateinit var btnBan: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminViewUsersBinding.inflate(inflater, container, false)
        initRecyclerView()

        btnOpenMenu = binding.flbuttonOpenMenu
        btnNewUser = binding.flbuttonNewUser
        btnDelete = binding.flbuttonDelete
        btnBan = binding.flbuttonBan

        binding.btnFlechaBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnOpenMenu.setOnClickListener {
            openFloatingMenu()
        }
        btnNewUser.setOnClickListener {}
        btnBan.setOnClickListener { }
        btnDelete.setOnClickListener { }

        return binding.root
    }

    fun initRecyclerView(){
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerViewUsers.adapter = UsersAdapter(UsersProvider.obtenerUsers())
    }

    private fun openFloatingMenu(){
        if(!open){
            btnOpenMenu.startAnimation(rotateOpen)

            btnDelete.isVisible = true
            btnBan.isVisible = true
            btnNewUser.isVisible = true
            btnDelete.isEnabled = true
            btnBan.isEnabled = true
            btnNewUser.isEnabled = true
            btnDelete.startAnimation(fromBottom)
            btnBan.startAnimation(fromBottom)
            btnNewUser.startAnimation(fromBottom)
            open = true
        }else{
            btnDelete.isEnabled = false
            btnBan.isEnabled = false
            btnNewUser.isEnabled = false
            btnOpenMenu.startAnimation(rotateClose)
            btnDelete.startAnimation(toBottom)
            btnBan.startAnimation(toBottom)
            btnNewUser.startAnimation(toBottom)

            btnDelete.isVisible = false
            btnBan.isVisible = false
            btnNewUser.isVisible = false
            open = false
        }
    }

}