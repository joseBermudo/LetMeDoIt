package cat.copernic.letmedoit.General.model.adapter

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Service
import cat.copernic.letmedoit.General.view.fragments.HomeFragmentDirections
import cat.copernic.letmedoit.General.view.fragments.PerfilUsuarioMenuSuperiorDirections
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Visitante.view.activities.Login
import cat.copernic.letmedoit.databinding.ServiceTemplateBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


const val SERVICE_ID = "id"
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
        Picasso.get().load(Uri.parse(serviceModel.image[0].img_link)).into(serviceImg)
        category.text =  serviceModel.category.id_category


        serviceFav.setOnClickListener { anadirFavorito() }
        service.setOnClickListener { goToService(serviceModel.id) }
    }

    private fun goToService(id: String) {

        val destinationLabel = Navigation.findNavController(itemView).currentDestination?.label
        var action  = HomeFragmentDirections.homeFragmentToViewService(serviceID = id)
        if(destinationLabel == "fragment_perfil_usuario_menu_superior")
            action = PerfilUsuarioMenuSuperiorDirections.userProfileToViewService(serviceID = id)

        Navigation.findNavController(itemView).navigate(action)
    }

    private fun anadirFavorito() {

        if (FirebaseAuth.getInstance().currentUser == null){
            binding.root.context.startActivity(Intent(binding.root.context, Login::class.java))
            return
        }
        favorite = !favorite
        if (favorite) serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_round_favorite_24)
        else serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.favorites_ion_colored)

    }

}