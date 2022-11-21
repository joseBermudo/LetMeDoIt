package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.databinding.ItemAdminViewUsersBinding

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