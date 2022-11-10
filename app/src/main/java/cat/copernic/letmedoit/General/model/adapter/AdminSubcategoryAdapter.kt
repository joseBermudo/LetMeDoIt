package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Subcategory
import cat.copernic.letmedoit.databinding.ItemListSubcategoryAdminBinding

class AdminSubcategoryAdapter(
    private val subcategoryList: List<Subcategory>
):RecyclerView.Adapter<AdminSubcategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminSubcategoryViewHolder {
        val binding = ItemListSubcategoryAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminSubcategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdminSubcategoryViewHolder, position: Int) {
        val item = subcategoryList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = subcategoryList.size

}