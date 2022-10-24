package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.R

class AdminCategoryAdapter(private val categoryList: List<Category>) : RecyclerView.Adapter<AdminCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AdminCategoryViewHolder(layoutInflater.inflate(R.layout.item_view_admin_categories,parent,false))
    }

    override fun onBindViewHolder(holder: AdminCategoryViewHolder, position: Int) {
        val item = categoryList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = categoryList.size

}