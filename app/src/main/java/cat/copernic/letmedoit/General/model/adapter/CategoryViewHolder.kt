package cat.copernic.letmedoit.General.model.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cat.copernic.letmedoit.General.model.Category

import cat.copernic.letmedoit.databinding.ItemCategoryBinding

class CategoryViewHolder(val binding: ItemCategoryBinding) : ViewHolder(binding.root)  {

    val categoryIcon = binding.itemCategoryIcon
    val categoryText = binding.itemCategoryText

    fun render(categoryModel: Category){
        categoryIcon.background = ContextCompat.getDrawable(binding.root.context,binding.root.resources.getIdentifier(categoryModel.image, "drawable","cat.copernic.letmedoit"))
        categoryText.text = categoryModel.nombre.replace(" ","\n")
    }
}