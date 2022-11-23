package cat.copernic.letmedoit.presentation.adapter.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemViewFavUserBinding
import cat.copernic.letmedoit.presentation.adapter.users.viewholder.FavUsersViewHolder

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