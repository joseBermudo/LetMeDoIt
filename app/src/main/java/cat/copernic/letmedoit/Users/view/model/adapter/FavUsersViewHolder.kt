package cat.copernic.letmedoit.Users.view.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users

import cat.copernic.letmedoit.databinding.ItemViewFavUserBinding

class FavUsersViewHolder(val binding: ItemViewFavUserBinding): RecyclerView.ViewHolder(binding.root) {
    val user_name = binding.txtUserName

    fun render(dealsModel: Users){

        user_name.text = dealsModel.name
    }
}