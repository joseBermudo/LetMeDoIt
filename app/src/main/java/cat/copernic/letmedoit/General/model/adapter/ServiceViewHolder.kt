package cat.copernic.letmedoit.General.model.adapter

import android.content.res.Resources
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Service
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.ServiceTemplateBinding
import com.squareup.picasso.Picasso

/**
 * Holder de Views.
 * @param binding Vista con binding de los items de categorias.
 * */
class ServiceViewHolder(val binding: ServiceTemplateBinding, val defaultfav : Boolean) : RecyclerView.ViewHolder(binding.root)  {

    //Instanciación de los controles del view.
    val service = binding.service
    val serviceImg = binding.serviceImg
    val serviceTitle = binding.serviceTitle
    val serviceDescription = binding.serviceDescription
    val serviceFav = binding.serviceFav
    val category = binding.serviceCategory
    var favorite = defaultfav

    /**
     * @param categoryModel El modelo de datos que se utilizara para asignar los datos a la view.
     * */
    fun render(serviceModel: Service){
        //Asigación de datos a los controles del view.
        serviceTitle.text = serviceModel.title
        serviceDescription.text = serviceModel.description
        Picasso.get().load(Uri.parse(serviceModel.image.img_link)).into(serviceImg)
        category.text =  serviceModel.category.id_category

        serviceFav.setOnClickListener { anadirFavorito() }
    }

    private fun anadirFavorito() {

        favorite = !favorite
        if (favorite) serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_round_favorite_24)
        else serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.favorites_ion_colored)

    }

}