package cat.copernic.letmedoit.Users.view.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Users
import cat.copernic.letmedoit.databinding.ItemViewFavUserBinding

class FavUsersAdapter(private val obtenerUsers: List<Users>): RecyclerView.Adapter<FavUsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavUsersViewHolder{
        val binding = ItemViewFavUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FavUsersViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavUsersViewHolder, position: Int) {

        val item = obtenerUsers[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = obtenerUsers.size
}