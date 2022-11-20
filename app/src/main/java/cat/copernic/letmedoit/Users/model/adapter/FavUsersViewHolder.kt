package cat.copernic.letmedoit.Users.model.adapter

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.data.Users
import cat.copernic.letmedoit.General.view.fragments.PerfilUsuarioMenuSuperiorDirections
import cat.copernic.letmedoit.General.view.fragments.profiles_services_manager_visDirections
import cat.copernic.letmedoit.databinding.ItemViewFavUserBinding

class FavUsersViewHolder(val binding: ItemViewFavUserBinding): RecyclerView.ViewHolder(binding.root) {
    val user_name = binding.txtUserName
    val favBtn = binding.userFav

    fun render(userModel: Users){

        user_name.text = userModel.name
        favBtn.setOnClickListener { (this.bindingAdapter as FavUsersAdapter).deleteFavUser(userModel) }
        binding.favUserLayout.setOnClickListener{ goToUserTopMenu("1") }
    }

    private fun goToUserTopMenu(userId : String) {
        val action = profiles_services_manager_visDirections.actionProfilesServicesManagerVisToPerfilUsuarioMenuSuperior(userID = userId)
        Navigation.findNavController(itemView).navigate(action)
    }
}