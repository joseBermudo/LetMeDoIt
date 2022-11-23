package cat.copernic.letmedoit.presentation.view.general.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cat.copernic.letmedoit.presentation.adapter.general.BarServicesOrProfilesAdapter
import cat.copernic.letmedoit.presentation.view.visitante.fragments.FavouriteProfilesSignIn
import cat.copernic.letmedoit.presentation.view.visitante.fragments.FavouriteServicesSignIn
import cat.copernic.letmedoit.databinding.FragmentProfilesServicesManagerVisBinding
import cat.copernic.letmedoit.presentation.view.users.fragments.VerListadoFavServices
import cat.copernic.letmedoit.presentation.view.users.fragments.viewFavUsers
import cat.copernic.letmedoit.presentation.viewmodel.general.BarProfileOrServicesViewModel
import cat.copernic.letmedoit.presentation.viewmodel.general.ProfilesServicesManagerViewModel
import com.google.firebase.auth.FirebaseAuth


class profiles_services_manager_vis : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    val currentUser = FirebaseAuth.getInstance().currentUser
    lateinit var model: ProfilesServicesManagerViewModel
    lateinit var adapter: FragmentStateAdapter
    lateinit var binding: FragmentProfilesServicesManagerVisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilesServicesManagerVisBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        var fragments: ArrayList<Fragment> = arrayListOf()

        if(currentUser==null){
            fragments = arrayListOf(
                FavouriteProfilesSignIn(),
                FavouriteServicesSignIn(),
            )
        }
        else{
            fragments = arrayListOf(
                viewFavUsers(),
                VerListadoFavServices(),
            )
        }

        adapter =
            BarServicesOrProfilesAdapter(
                this.childFragmentManager,
                fragments,
                lifecycle
            )

        model = ViewModelProvider(requireActivity())[ProfilesServicesManagerViewModel::class.java]

        binding.viewPagerProfileOrServices.adapter = adapter

        binding.viewPagerProfileOrServices.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                model.sendPagePosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity())[BarProfileOrServicesViewModel::class.java]
        model.option.observe(viewLifecycleOwner, Observer {
            binding.viewPagerProfileOrServices.currentItem = it
        })
    }
}