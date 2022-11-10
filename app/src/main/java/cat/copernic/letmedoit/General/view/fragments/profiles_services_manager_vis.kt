package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cat.copernic.letmedoit.General.model.adapter.BarServicesOrProfilesAdapter
import cat.copernic.letmedoit.General.model.adapter.UserTopMenuAdapter
import cat.copernic.letmedoit.General.viewmodel.BarProfileOrServicesViewModel
import cat.copernic.letmedoit.General.viewmodel.ProfilesServicesManagerViewModel
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Visitante.view.fragments.FavouriteProfilesSignIn
import cat.copernic.letmedoit.Visitante.view.fragments.FavouriteServicesSignIn
import cat.copernic.letmedoit.databinding.FragmentFavouriteProfilesSignInBinding
import cat.copernic.letmedoit.databinding.FragmentFavouriteServicesSignInBinding
import cat.copernic.letmedoit.databinding.FragmentProfilesServicesManagerVisBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profiles_services_manager_vis.newInstance] factory method to
 * create an instance of this fragment.
 */
class profiles_services_manager_vis : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var model : ProfilesServicesManagerViewModel
    lateinit var adapter : FragmentStateAdapter
    lateinit var binding : FragmentProfilesServicesManagerVisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilesServicesManagerVisBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val fragments : ArrayList<Fragment> = arrayListOf(
            FavouriteProfilesSignIn(),
            FavouriteServicesSignIn(),
        )
        adapter = BarServicesOrProfilesAdapter(this.childFragmentManager,fragments,lifecycle)

        model  = ViewModelProvider(requireActivity())[ProfilesServicesManagerViewModel::class.java]

        binding.viewPagerProfileOrServices.adapter = adapter

        binding.viewPagerProfileOrServices.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                model.sendPagePosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int,
                                        positionOffset: Float,
                                        positionOffsetPixels: Int) {
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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profiles_services_manager_vis.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profiles_services_manager_vis().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}