package cat.copernic.letmedoit.presentation.adapter.users.viewholder

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding

class DealsViewHolder(val binding: ItemVerDealsBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName

    fun render(dealsModel: Deal){

        user_name.text = dealsModel.description
        binding.cardvVerDeals.setOnClickListener{ onClickItem() }
    }

    private fun onClickItem() {
        Navigation.findNavController(itemView).navigate(R.id.viewDeal)
    }
}