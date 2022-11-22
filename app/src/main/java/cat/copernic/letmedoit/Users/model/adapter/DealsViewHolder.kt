package cat.copernic.letmedoit.Users.model.adapter

import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Users.model.data.Deal
import cat.copernic.letmedoit.Users.view.fragments.verConversaciones
import cat.copernic.letmedoit.Users.view.fragments.verListadoDealsDirections
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