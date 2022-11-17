package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cat.copernic.letmedoit.General.model.adapter.BarChatsOrDealsAdapter
import cat.copernic.letmedoit.General.viewmodel.BarChatsOrDealsViewModel
import cat.copernic.letmedoit.General.viewmodel.ChatsDealsManagerViewModel
import cat.copernic.letmedoit.Users.view.fragments.verConversaciones
import cat.copernic.letmedoit.Users.view.fragments.verListadoDeals
import cat.copernic.letmedoit.databinding.FragmentChatsDealsManagerVisBinding


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

        adapter = BarChatsOrDealsAdapter(this.childFragmentManager,fragments,lifecycle)
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