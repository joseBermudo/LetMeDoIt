package cat.copernic.letmedoit.General.model.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ItemListCategoryAdminBinding
import com.bumptech.glide.Glide

class AdminCategoryViewHolder(val binding: ItemListCategoryAdminBinding) : RecyclerView.ViewHolder(binding.root) {

    val categoryIcon = binding.imgCategory
    val categoryName = binding.txtCategoryName
    fun render(categoryModel: Category){
        categoryIcon.background= ContextCompat.getDrawable(binding.root.context,binding.root.resources.getIdentifier(categoryModel.image, "drawable","cat.copernic.letmedoit"))
        categoryName.text = categoryModel.nombre.replace(" ","\n")
    }
}