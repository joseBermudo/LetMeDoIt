package cat.copernic.letmedoit.General.model.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding

class AdminCategoryViewHolder(val binding: ItemListCategoryAdminBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val categoryIcon = binding.imgCategory
    val categoryName = binding.txtCategoryName

    fun render(
        categoryModel: cat.copernic.letmedoit.General.model.Category,
        onClickListener: (cat.copernic.letmedoit.General.model.Category) -> Unit,
        onClickDelete: (Int) -> Unit
    ) {
        categoryIcon.background = ContextCompat.getDrawable(
            binding.root.context,
            binding.root.resources.getIdentifier(
                categoryModel.image,
                "drawable",
                "cat.copernic.letmedoit"
            )
        )
        categoryName.text = categoryModel.nombre.replace(" ", "\n")
        itemView.setOnClickListener { onClickListener(categoryModel) }
        binding.itemCategoryDeleteIcon.setOnClickListener { onClickDelete(absoluteAdapterPosition) }
    }
}