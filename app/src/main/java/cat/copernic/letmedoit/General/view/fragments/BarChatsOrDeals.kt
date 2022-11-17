package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cat.copernic.letmedoit.General.viewmodel.BarChatsOrDealsViewModel
import cat.copernic.letmedoit.General.viewmodel.ChatsDealsManagerViewModel
import cat.copernic.letmedoit.General.viewmodel.ProfilesServicesManagerViewModel
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentBarChatsOrDealsBinding
import com.google.android.material.tabs.TabLayout

class BarChatsOrDeals : Fragment() {

    lateinit var model : BarChatsOrDealsViewModel
    lateinit var binding : FragmentBarChatsOrDealsBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBarChatsOrDealsBinding.inflate(inflater,container,false)
        model = ViewModelProvider(requireActivity())[BarChatsOrDealsViewModel::class.java]

        binding.chatsOrDeals.addOnTabSelectedListener(object :
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
        val modelViewPagerSelector = ViewModelProvider(requireActivity())[ChatsDealsManagerViewModel::class.java]
        modelViewPagerSelector.pagePosition.observe(viewLifecycleOwner, Observer{
            binding.chatsOrDeals.getTabAt(it)?.select()
        })
    }

}