package cat.copernic.letmedoit.presentation.adapter.general.viewholder

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Service
import cat.copernic.letmedoit.presentation.view.general.fragments.*
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.di.FirebaseModule
import cat.copernic.letmedoit.presentation.view.visitante.activities.Login
import cat.copernic.letmedoit.databinding.ServiceTemplateBinding
import cat.copernic.letmedoit.presentation.adapter.general.ServiceAdapter
import com.squareup.picasso.Picasso


const val SERVICE_ID = "id"
/**
 * Holder de Views.
 * @param binding Vista con binding de los items de categorias.
 * */
class ServiceViewHolder(val binding: ServiceTemplateBinding) : RecyclerView.ViewHolder(binding.root)  {

    //Instanciación de los controles del view.
    val service = binding.service
    val serviceImg = binding.serviceImg
    val serviceTitle = binding.serviceTitle
    val serviceDescription = binding.serviceDescription
    val serviceFav = binding.serviceFav
    val category = binding.serviceCategory

    /**
     * @param categoryModel El modelo de datos que se utilizara para asignar los datos a la view.
     * */
    fun render(serviceModel: Service){
        if(serviceModel.userid == Constants.USER_LOGGED_IN_ID)
            serviceFav.isVisible = false

        //Asigación de datos a los controles del view.
        serviceTitle.text = serviceModel.title
        serviceDescription.text = serviceModel.description
        Picasso.get().load(Uri.parse(serviceModel.image[0].img_link)).into(serviceImg)
        category.text =  serviceModel.category.id_category

        if (serviceModel.defaultFav)serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_round_favorite_24)
        else serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.favorites_ion_colored)

        serviceFav.setOnClickListener { manageFavorite(serviceModel) }
        service.setOnClickListener { goToService(serviceModel) }
    }

    private fun manageFavorite(serviceModel: Service) {
        serviceModel.defaultFav = !serviceModel.defaultFav
        if (FirebaseModule.firebaseAuthProvider().currentUser == null){
            binding.root.context.startActivity(Intent(binding.root.context, Login::class.java))
            return
        }
        if(serviceModel.defaultFav) {
            (bindingAdapter as ServiceAdapter).addFavService(serviceModel)
            serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_round_favorite_24)
        }
        else{
            (bindingAdapter as ServiceAdapter).deleteFavService(serviceModel)
            serviceFav.background = ContextCompat.getDrawable(binding.root.context, R.drawable.favorites_ion_colored)
        }
    }

    private fun goToService(service: Service) {
        val destinationLabel = Navigation.findNavController(itemView).currentDestination?.label

        val action = when(destinationLabel){
            "fragment_perfil_usuario_menu_superior" -> PerfilUsuarioMenuSuperiorDirections.userProfileToViewService(service)
            "fragment_profiles_services_manager_vis" -> profiles_services_manager_visDirections.actionProfilesServicesManagerVisToViewService(service)
            else -> HomeFragmentDirections.homeFragmentToViewService(service)
        }

        Navigation.findNavController(itemView).navigate(action)
    }
}