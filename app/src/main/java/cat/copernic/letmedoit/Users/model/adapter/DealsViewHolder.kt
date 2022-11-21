package cat.copernic.letmedoit.Users.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding

class DealsViewHolder(val binding: ItemVerDealsBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName

    fun render(dealsModel: Users, onClickRecyclerDeals: (Users) -> Unit){

        user_name.text = dealsModel.name
        binding.cardvVerDeals.setOnClickListener{onClickRecyclerDeals(dealsModel)}
    }
}