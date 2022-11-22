package cat.copernic.letmedoit.presentation.adapter.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemAdminViewUsersBinding
import cat.copernic.letmedoit.presentation.adapter.general.viewholder.UsersViewHolder

class UsersAdapter(private val obtenerUsers:ArrayList<Users>) : RecyclerView.Adapter<UsersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = ItemAdminViewUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val item = obtenerUsers[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = obtenerUsers.size


}