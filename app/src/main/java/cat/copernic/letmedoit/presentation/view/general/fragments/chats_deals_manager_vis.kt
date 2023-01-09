package cat.copernic.letmedoit.presentation.view.general.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.presentation.adapter.general.BarChatsOrDealsAdapter
import cat.copernic.letmedoit.databinding.FragmentChatsDealsManagerVisBinding
import cat.copernic.letmedoit.presentation.view.users.fragments.verConversaciones
import cat.copernic.letmedoit.presentation.view.users.fragments.verListadoDeals
import cat.copernic.letmedoit.presentation.viewmodel.general.BarChatsOrDealsViewModel
import cat.copernic.letmedoit.presentation.viewmodel.general.ChatsDealsManagerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class chats_deals_manager_vis : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    lateinit var model: ChatsDealsManagerViewModel
    lateinit var adapter: FragmentStateAdapter
    lateinit var binding: FragmentChatsDealsManagerVisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentChatsDealsManagerVisBinding.inflate(inflater,container,false)


        val fragments : ArrayList<Fragment> = arrayListOf(
            verConversaciones(),
            verListadoDeals(),
        )

        adapter = BarChatsOrDealsAdapter(
            this.childFragmentManager,
            fragments,
            lifecycle
        )
        model  = ViewModelProvider(requireActivity())[ChatsDealsManagerViewModel::class.java]
        binding.viewPagerChatsOrDeals.adapter = adapter
        binding.viewPagerChatsOrDeals.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
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
        binding.viewPagerChatsOrDeals.currentItem = 0
        val currentDestinationLabel =  findNavController().previousBackStackEntry?.destination?.label
        if(currentDestinationLabel == "fragment_rate_user" || currentDestinationLabel == "fragment_ver_deal" || currentDestinationLabel == "fragment_conclude_deal")
            binding.viewPagerChatsOrDeals.currentItem = 1
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model = ViewModelProvider(requireActivity())[BarChatsOrDealsViewModel::class.java]
        model.option.observe(viewLifecycleOwner, Observer {
            binding.viewPagerChatsOrDeals.currentItem = it
        })
    }

}