package cat.copernic.letmedoit.Users.view.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Users
import cat.copernic.letmedoit.databinding.ItemVerConversacionesBinding

class ConversacionesAdapter(private val obtenerUsers:List<Users>) : RecyclerView.Adapter<ConversacionesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversacionesViewHolder{

        val binding = ItemVerConversacionesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversacionesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ConversacionesViewHolder, position: Int){
        val item = obtenerUsers[position]
        holder.render(item)

    }

    override fun getItemCount(): Int = obtenerUsers.size
}