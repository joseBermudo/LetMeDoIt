package cat.copernic.letmedoit.Admin.model.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Category
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryViewHolder(val binding: ItemListCategoryAdminBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val categoryIcon = binding.imgCategory
    val categoryName = binding.txtCategoryName
    val subcategoryNumber = binding.txtSubcategoryNumber
    fun render(
        categoryModel: Category,
        onClickListener: (Category) -> Unit,
        onClickDelete: (Int) -> Unit,
        onClickEdit: (Category) -> Unit
    ) {
        categoryIcon.background = ContextCompat.getDrawable(
            binding.root.context,
            binding.root.resources.getIdentifier(
                categoryModel.image,
                "drawable",
                "cat.copernic.letmedoit"
            )
        )
        categoryName.text = categoryModel.nombre.replace("_","\n")
        subcategoryNumber.text = categoryModel.subcategories.size.toString()
        itemView.setOnClickListener { onClickListener(categoryModel) }
        binding.itemCategoryDeleteIcon.setOnClickListener { onClickDelete(absoluteAdapterPosition)}
        binding.itemCategoryEditIcon.setOnClickListener {onClickEdit(categoryModel) }
    }
}