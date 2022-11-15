package cat.copernic.letmedoit.General.model.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Image
import cat.copernic.letmedoit.databinding.ImagesListTemplateBinding

class ImagesAdapter (private var imagesList : ArrayList<Image>) : RecyclerView.Adapter<ImagesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImagesListTemplateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = imagesList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    fun removeAll(){
        imagesList.removeAll(imagesList)
    }

    fun removeItems(){
        val imagesToRemove = ArrayList<Image>()

        imagesToRemove.addAll(imagesList.filter { it.checked == true })

        imagesList.removeAll(imagesToRemove)


        notifyDataSetChanged()
    }

    fun selectAll() {
        imagesList.forEach { it.checked = true }
        notifyDataSetChanged()
    }
}