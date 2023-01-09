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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.data.provider.UsersProvider
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import cat.copernic.letmedoit.databinding.FragmentAdminViewUsersBinding
import cat.copernic.letmedoit.presentation.adapter.general.UsersAdapter
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.bumptech.glide.Glide.init
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    private var _binding: FragmentAdminViewUsersBinding? = null
    private val binding get() = _binding!!
    private var usersList = ArrayList<Users>()
    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UsersAdapter
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


        init()


        viewModel.getAllUserState.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        usersList =
                            ArrayList(dataState.data)
                        initRecyclerView()
                    }
                    is DataState.Error -> {
                        Utils.showOkDialog(
                            "Error: ",
                            requireContext(),
                            dataState.exception.message.toString()
                        )


                    }
                    is DataState.Loading -> {

                    }
                    else -> Unit
                }
            })



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
        btnBan.setOnClickListener {
            banearUsuarios()
        }




        return binding.root
    }

    private fun banearUsuarios() {

        usersList.forEachIndexed { i, user ->
            if (user.check == true) {
                if (user.banned == true) {
                    viewModel.updateBan(user.id, false)
                    usersList.get(i).banned = false
                } else {
                    viewModel.updateBan(user.id, true)
                    usersList.get(i).banned = true
                }
                adapter.notifyItemChanged(i)

            }
        }
    }

    fun initRecyclerView() {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(binding.root.context)
        adapter =
            UsersAdapter(usersList, onClickCheckBox = { user -> checkTheBox(user) })

        binding.recyclerViewUsers.adapter = adapter
    }

    private fun checkTheBox(user: Users) {
        if (user.check) {
            user.check = false
        } else {
            user.check = true
        }
    }

    private fun openFloatingMenu() {
        if (!open) {
            btnOpenMenu.startAnimation(rotateOpen)


            btnBan.isVisible = true
            btnNewUser.isVisible = true
            btnBan.isEnabled = true
            btnNewUser.isEnabled = true
            btnBan.startAnimation(fromBottom)
            btnNewUser.startAnimation(fromBottom)
            open = true
        } else {
            btnBan.isEnabled = false
            btnNewUser.isEnabled = false
            btnOpenMenu.startAnimation(rotateClose)
            btnBan.startAnimation(toBottom)
            btnNewUser.startAnimation(toBottom)
            btnBan.isVisible = false
            btnNewUser.isVisible = false
            open = false
        }
    }

    private fun init() {
        viewModel.getAllUser()
    }

}