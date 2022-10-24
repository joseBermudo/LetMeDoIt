package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ItemCategoryBinding
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryAdapter(private val categoryList: List<Category>) : RecyclerView.Adapter<AdminCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminCategoryViewHolder {
        val binding = ItemListCategoryAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminCategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = categoryList.size

}