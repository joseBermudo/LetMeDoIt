package cat.copernic.letmedoit.General.model.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Image
import cat.copernic.letmedoit.R
import com.squareup.picasso.Picasso


class SliderImagesViewHolder(val view : View) : RecyclerView.ViewHolder(view)  {

    val imageView: ImageView = view.findViewById(R.id.slider_image)

    private var isImageFitToScreen = false

    fun setImage(curImg: Image) {
        Picasso.get().load(Uri.parse(curImg.img_link)).into(imageView)
    }
}