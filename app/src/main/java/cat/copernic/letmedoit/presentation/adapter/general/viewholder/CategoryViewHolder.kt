package cat.copernic.letmedoit.presentation.adapter.general.viewholder

import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.databinding.ItemCategoryTemplateBinding
import cat.copernic.letmedoit.presentation.view.general.fragments.HomeFragmentDirections

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

        binding.itemCategory.setOnClickListener{ applyFilter(categoryModel) }
    }

    private fun applyFilter(categoryModel: Category) {
        val action = HomeFragmentDirections.actionHomeFragmentSelf(3,categoryModel)
        Navigation.findNavController(itemView).navigate(action)
    }
}