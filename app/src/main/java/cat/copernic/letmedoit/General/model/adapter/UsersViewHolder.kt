package cat.copernic.letmedoit.General.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Users
import cat.copernic.letmedoit.databinding.ItemAdminViewUsersBinding

class UsersViewHolder(val binding: ItemAdminViewUsersBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName
    val ban_users = binding.imgUserBanned

    fun render(usersModel: Users){
        user_name.text = usersModel.name.replace(" ", "\n")

    }
}