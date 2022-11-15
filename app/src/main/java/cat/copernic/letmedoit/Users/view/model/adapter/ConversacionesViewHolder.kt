package cat.copernic.letmedoit.Users.view.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Users
import cat.copernic.letmedoit.databinding.ItemVerConversacionesBinding

class ConversacionesViewHolder(val binding: ItemVerConversacionesBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName

    fun render(conversacionesModel: Users){
        user_name.text = conversacionesModel.name

    }
}