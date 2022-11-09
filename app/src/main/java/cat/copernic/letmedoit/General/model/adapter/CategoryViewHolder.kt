package cat.copernic.letmedoit.General.model.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.databinding.ItemCategoryTemplateBinding

/**
 * Holder de Views.
 * @param binding Vista con binding de los items de categorias.
 * */
class CategoryViewHolder(val binding: ItemCategoryTemplateBinding,) : ViewHolder(binding.root)  {

    //Instanciación de los controles del view.
    val categoryIcon = binding.itemCategoryIcon
    val categoryText = binding.itemCategoryText

    /**
     * @param categoryModel El modelo de datos que se utilizara para asignar los datos a la view.
     * */
    fun render(categoryModel: Category){
        //Asigación de datos a los controles del view.
        categoryIcon.background = ContextCompat.getDrawable(binding.root.context,binding.root.resources.getIdentifier(categoryModel.image, "drawable","cat.copernic.letmedoit"))
        categoryText.text = categoryModel.nombre.replace(" ","\n")

        binding.itemCategory.setOnClickListener{ binding.root.findNavController().navigate(R.id.homeFragment) }
    }
}