package cat.copernic.letmedoit.Admin.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryAdapter(
    private val categoryList: List<Category>,
    private val onClickListener: (Category) -> Unit,
    private val onClickDelete:(Int) -> Unit
) : RecyclerView.Adapter<AdminCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminCategoryViewHolder {
        val binding =
            ItemListCategoryAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdminCategoryViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: AdminCategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.render(item, onClickListener, onClickDelete)
    }

    override fun getItemCount(): Int = categoryList.size

}