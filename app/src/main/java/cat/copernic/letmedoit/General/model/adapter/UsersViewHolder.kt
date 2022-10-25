package cat.copernic.letmedoit.General.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Users
import cat.copernic.letmedoit.databinding.ItemViewBannedUsersBinding

class UsersViewHolder(val binding: ItemViewBannedUsersBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName


    fun render(usersModel: Users){
        user_name.text = usersModel.name.replace(" ", "\n")

    }
}