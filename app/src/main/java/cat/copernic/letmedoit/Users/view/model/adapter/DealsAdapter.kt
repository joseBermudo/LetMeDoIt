package cat.copernic.letmedoit.Users.view.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding

class DealsAdapter(
    private val obtenerUsers: List<Users>,
    private val onClickRecyclerDeals: (Users) -> Unit
    ): RecyclerView.Adapter<DealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {

        val binding = ItemVerDealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {

        val item = obtenerUsers[position]
        holder.render(item,onClickRecyclerDeals)
    }

    override fun getItemCount(): Int = obtenerUsers.size
}