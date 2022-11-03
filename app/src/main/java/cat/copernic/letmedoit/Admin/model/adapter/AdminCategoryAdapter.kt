package cat.copernic.letmedoit.Admin.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Admin.model.Category
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryAdapter(
    private val categoryList: List<cat.copernic.letmedoit.Admin.model.Category>,
    private val onClickListener: (cat.copernic.letmedoit.Admin.model.Category) -> Unit,
    private val onClickDelete:(Int) -> Unit
) : RecyclerView.Adapter<cat.copernic.letmedoit.Admin.model.adapter.AdminCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cat.copernic.letmedoit.Admin.model.adapter.AdminCategoryViewHolder {
        val binding =
            ItemListCategoryAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return cat.copernic.letmedoit.Admin.model.adapter.AdminCategoryViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: cat.copernic.letmedoit.Admin.model.adapter.AdminCategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.render(item, onClickListener, onClickDelete)
    }

    override fun getItemCount(): Int = categoryList.size

}