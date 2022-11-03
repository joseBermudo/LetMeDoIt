package cat.copernic.letmedoit.Admin.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Admin.model.Category
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ItemCategoryTemplateBinding

/**
 * Adaptador de Categorias.
 * @param categoryList ArrayList de categorias.
 * */
class CategoryAdapter(private val categoryList:ArrayList<cat.copernic.letmedoit.Admin.model.Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

    /**
     * Función ejecutada al crear el View Holder. infla el XML del item de categorias.
     * @param parent Padre
     * @param viewType Tipo de vista
     * @return Devuelve un CategoryViewHolder cargando la vista con los datos del listado.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    /**
     * Función que renderiza el item en cuestión desde la función del ViewHolder.
     * */
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.render(item)
    }

    /**
     * @return Devuelve el tamaño del listado.
     * */
    override fun getItemCount(): Int {
        return categoryList.size
    }


}