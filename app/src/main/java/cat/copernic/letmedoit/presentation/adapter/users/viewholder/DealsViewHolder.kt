package cat.copernic.letmedoit.presentation.adapter.users.viewholder

import android.annotation.SuppressLint
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Utils.datahepers.DealsUsersServicesJoin
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding
import cat.copernic.letmedoit.presentation.view.general.fragments.chats_deals_manager_visDirections
import com.squareup.picasso.Picasso

class DealsViewHolder(val binding: ItemVerDealsBinding): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun render(dealToShow: DealsUsersServicesJoin){

        binding.txtUserName.text = "${dealToShow.userTwo.name} ${dealToShow.userTwo.surname} \n @${dealToShow.userTwo.username}"
        binding.txtServiceName.text = dealToShow.ServiceTwo.title
        if(dealToShow.userTwo.avatar != "") Picasso.get().load(dealToShow.userTwo.avatar).into(binding.imgProfile)

        binding.cardvVerDeals.setOnClickListener{ onClickItem(dealToShow.deal,dealToShow.userTwo) }
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