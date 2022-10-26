package cat.copernic.letmedoit.General.model.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Image
import cat.copernic.letmedoit.General.model.Service
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentViewServiceBinding
import com.squareup.picasso.Picasso

class SliderImagesViewHolder(val view : View) : RecyclerView.ViewHolder(view)  {

    val imageView = view.findViewById<ImageView>(R.id.slider_image)

    fun setImage(curImg: Image) {
        Picasso.get().load(Uri.parse(curImg.img_link)).into(imageView)
    }
}