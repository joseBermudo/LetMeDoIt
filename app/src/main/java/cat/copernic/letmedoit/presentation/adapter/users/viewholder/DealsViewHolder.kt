package cat.copernic.letmedoit.presentation.adapter.users.viewholder

import android.annotation.SuppressLint
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding
import cat.copernic.letmedoit.presentation.view.general.fragments.chats_deals_manager_visDirections
import com.squareup.picasso.Picasso

class DealsViewHolder(val binding: ItemVerDealsBinding): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun render(deal: Deal, user: Users, service: Service){

        binding.txtUserName.text = "${user.name} ${user.surname} \n @${user.username}"
        binding.txtServiceName.text = service.title
        if(user.avatar != "") Picasso.get().load(user.avatar).into(binding.imgProfile)

        binding.cardvVerDeals.setOnClickListener{ onClickItem(deal,user) }
    }

    private fun onClickItem(deal: Deal, user: Users) {
        if (deal.accepted){
            val action  = chats_deals_manager_visDirections.actionVerConversacionesToConcludeDeal(deal, user)
            Navigation.findNavController(itemView).navigate(action)
        }
        else{
            val action  = chats_deals_manager_visDirections.actionVerConversacionesToViewDeal(deal, user)
            Navigation.findNavController(itemView).navigate(action)
        }
    }
}