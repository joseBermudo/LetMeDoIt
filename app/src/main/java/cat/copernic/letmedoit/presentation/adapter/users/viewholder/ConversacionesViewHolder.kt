package cat.copernic.letmedoit.presentation.adapter.users.viewholder

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemVerConversacionesBinding


class ConversacionesViewHolder(val binding: ItemVerConversacionesBinding): RecyclerView.ViewHolder(binding.root) {

    val user_name = binding.txtUserName



    fun render(conversacionesModel: Users, onClickRecyclerV: (Users) -> Unit) {
        user_name.text = conversacionesModel.name
        binding.cardvConversaciones.setOnClickListener {onClickRecyclerV(conversacionesModel)}
    }
}