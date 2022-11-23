package cat.copernic.letmedoit.presentation.adapter.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Opinions
import cat.copernic.letmedoit.databinding.OpinionsUserTemplateBinding
import cat.copernic.letmedoit.presentation.adapter.general.viewholder.OpinionsViewHolder

class OpinionsAdapter(private var opinionsList:ArrayList<Opinions>) : RecyclerView.Adapter<OpinionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpinionsViewHolder {
        val binding = OpinionsUserTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OpinionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OpinionsViewHolder, position: Int) {
        val item = opinionsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return opinionsList.size
    }


}
