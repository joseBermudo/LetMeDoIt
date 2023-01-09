package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.UserFavoriteProfiles
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.presentation.adapter.users.FavUsersAdapter
import cat.copernic.letmedoit.databinding.FragmentViewFavUsersBinding
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class viewFavUsers : Fragment() {

    private var _binding: FragmentViewFavUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val userViewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewFavUsersBinding.inflate(inflater, container, false)
        //initRecyclerView()
        userViewModel.getFavoriteProfiles()
        initObservers()
        return binding.root
    }

    private var totalFavUsers = 0
    private var obtainedFavUsers = 0
    private var users = ArrayList<Users>()
    private fun initObservers() {
        userViewModel.getFavoriteProfilesState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<ArrayList<UserFavoriteProfiles>> -> {
                    if(::adapter.isInitialized) adapter.clear()

                    totalFavUsers = dataState.data.size
                    if (totalFavUsers == 0) hideProgress()

                    dataState.data.forEach { userViewModel.getUser(it.favorite_profile_id) }
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    hideProgress()
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        } )
        userViewModel.getUserState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Users?> -> {
                    if(dataState.data != null){
                        users.add(dataState.data)
                        obtainedFavUsers++
                    }
                    if(obtainedFavUsers == totalFavUsers) initRecyclerView(users)
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                    hideProgress()
                }
                is DataState.Loading -> {
                }
                else -> Unit
            }
        } )
        userViewModel.deleteFavoriteProfileState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState){
                is DataState.Success<Boolean> -> {
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ",requireContext(),dataState.exception.message.toString())
                }
                is DataState.Loading -> {
                }
                else -> Unit
            }
        } )
    }

    private fun hideProgress() {
        binding.loadingFavUsers.isVisible = false
    }

    private fun showProgress() {
        binding.loadingFavUsers.isVisible = true
    }

    fun deleteFavoriteProfile(idProfile : String){
        userViewModel.deleteFavoriteProfile(idProfile)
    }
    private lateinit var adapter : FavUsersAdapter
    fun initRecyclerView(users: ArrayList<Users>) {
        hideProgress()
        binding.recyclerViewFavUser.layoutManager = GridLayoutManager(binding.root.context, 2)
        adapter = FavUsersAdapter(users,this)
        binding.recyclerViewFavUser.adapter = adapter
        obtainedFavUsers = 0
    }
}