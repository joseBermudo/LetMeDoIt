package cat.copernic.letmedoit.General.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Image
import cat.copernic.letmedoit.R

class SliderImagesAdapter(private val images : ArrayList<Image>) : RecyclerView.Adapter<SliderImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderImagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_image_slider,parent,false)
        return SliderImagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderImagesViewHolder, position: Int) {
        val curImg = images[position]
        holder.setImage(curImg)
    }

    override fun getItemCount(): Int {
        return images.size
    }

}