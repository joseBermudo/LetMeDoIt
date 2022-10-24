package cat.copernic.letmedoit.General.model.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.R

class AdminCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val category = view.findViewById<TextView>(R.id.text_titleCategories)
    fun render(categoryModel: Category){
        category.text = categoryModel.nombre
    }
}