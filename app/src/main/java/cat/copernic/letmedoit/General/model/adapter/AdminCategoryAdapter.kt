package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryAdapter(
    private val categoryList: List<Category>,
    private val onClickListener: (Category) -> Unit,
    private val onClickDelete:(Int) -> Unit,
    private val onClickEdit:(Category) -> Unit
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
        holder.render(item, onClickListener, onClickDelete,onClickEdit)
    }

    override fun getItemCount(): Int = categoryList.size

}