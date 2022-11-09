package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cat.copernic.letmedoit.General.viewmodel.BarProfileOrServicesViewModel
import cat.copernic.letmedoit.General.viewmodel.ProfilesServicesManagerViewModel
import cat.copernic.letmedoit.General.viewmodel.SearchViewViewModel
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentBarProfilesOrServicesBinding
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BarProfilesOrServices.newInstance] factory method to
 * create an instance of this fragment.
 */
class BarProfilesOrServices : Fragment() {
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

    lateinit var model : BarProfileOrServicesViewModel
    lateinit var binding : FragmentBarProfilesOrServicesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BarProfilesOrServices.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarProfilesOrServices().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}