package cat.copernic.letmedoit.presentation.adapter.general.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemAdminViewUsersBinding

class UsersViewHolder(val binding: ItemAdminViewUsersBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName
    val ban_users = binding.imgUserBanned
    fun render(usersModel: Users, onClickCheckBox: (Users) -> Unit) {
        user_name.text = usersModel.name?.replace(" ", "\n") ?: ""
        if(usersModel.banned == true){
            binding.imgUserBanned.isVisible = true
        }else{
            binding.imgUserBanned.isVisible = false
        }

    }
}