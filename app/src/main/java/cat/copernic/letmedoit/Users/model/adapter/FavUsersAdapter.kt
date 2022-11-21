package cat.copernic.letmedoit.Users.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.databinding.ItemViewFavUserBinding

class FavUsersAdapter(private val obtenerUsers: ArrayList<Users>): RecyclerView.Adapter<FavUsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavUsersViewHolder {
        val binding = ItemViewFavUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavUsersViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavUsersViewHolder, position: Int) {

        val item = obtenerUsers[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = obtenerUsers.size

    fun deleteFavUser(user: Users){
        obtenerUsers.remove(user)
        notifyDataSetChanged()
    }
}