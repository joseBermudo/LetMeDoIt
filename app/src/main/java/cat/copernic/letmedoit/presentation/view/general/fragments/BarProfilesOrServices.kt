package cat.copernic.letmedoit.presentation.view.general.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cat.copernic.letmedoit.databinding.FragmentBarProfilesOrServicesBinding
import cat.copernic.letmedoit.presentation.viewmodel.general.BarProfileOrServicesViewModel
import cat.copernic.letmedoit.presentation.viewmodel.general.ProfilesServicesManagerViewModel
import com.google.android.material.tabs.TabLayout


class BarProfilesOrServices : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var model : BarProfileOrServicesViewModel
    lateinit var binding : FragmentBarProfilesOrServicesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBarProfilesOrServicesBinding.inflate(inflater,container,false)
        model  = ViewModelProvider(requireActivity())[BarProfileOrServicesViewModel::class.java]


        binding.profilesOrServices.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    model.sendOption(tab.position)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modelViewPagerSelector = ViewModelProvider(requireActivity())[ProfilesServicesManagerViewModel::class.java]
        modelViewPagerSelector.pagePosition.observe(viewLifecycleOwner, Observer {
            binding.profilesOrServices.getTabAt(it)?.select()
        })
    }
}