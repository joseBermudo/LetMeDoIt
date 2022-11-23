package cat.copernic.letmedoit.presentation.adapter.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemVerConversacionesBinding
import cat.copernic.letmedoit.presentation.adapter.users.viewholder.ConversacionesViewHolder

class ConversacionesAdapter(
    private val obtenerUsers: List<Users>,
    private val onClickRecyclerV: (Users) -> Unit
) : RecyclerView.Adapter<ConversacionesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversacionesViewHolder {

        val binding =
            ItemVerConversacionesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversacionesViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ConversacionesViewHolder, position: Int) {
        val item = obtenerUsers[position]
        holder.render(item, onClickRecyclerV)

    }

    override fun getItemCount(): Int = obtenerUsers.size
}