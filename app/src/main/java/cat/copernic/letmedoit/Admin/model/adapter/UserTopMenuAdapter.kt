package cat.copernic.letmedoit.Admin.model.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import cat.copernic.letmedoit.Admin.model.Service
import java.util.*
import kotlin.collections.ArrayList


class UserTopMenuAdapter(fragmentManager: FragmentManager, val items: ArrayList<Fragment>, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}