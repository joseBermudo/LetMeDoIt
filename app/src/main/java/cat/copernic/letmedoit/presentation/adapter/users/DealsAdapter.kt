package cat.copernic.letmedoit.presentation.adapter.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Deal
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.ItemVerDealsBinding
import cat.copernic.letmedoit.presentation.adapter.users.viewholder.DealsViewHolder

class DealsAdapter(
    private val obtenerDeals: List<Deal>,
    private val users: ArrayList<Users>,
    private val services: ArrayList<Service>,
) : RecyclerView.Adapter<DealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {

        val binding =
            ItemVerDealsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {

        val item = obtenerDeals[position]
        holder.render(item,users[position],services[position])
    }

    override fun getItemCount(): Int = obtenerDeals.size
}