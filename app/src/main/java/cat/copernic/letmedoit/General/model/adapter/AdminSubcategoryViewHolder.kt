package cat.copernic.letmedoit.General.model.adapter

import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Subcategory
import cat.copernic.letmedoit.databinding.ItemListSubcategoryAdminBinding

class AdminSubcategoryViewHolder(
    val binding: ItemListSubcategoryAdminBinding
): RecyclerView.ViewHolder(binding.root) {

    val subcategoryName = binding.txtSubcategoryName

    fun render(
        subcategoryModel: Subcategory
    ){

        subcategoryName.text = subcategoryModel.nombre.replace("_","\n")

    }
}