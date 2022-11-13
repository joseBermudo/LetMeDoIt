package cat.copernic.letmedoit.General.model.adapter

import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Opinions
import cat.copernic.letmedoit.General.view.fragments.PerfilUsuarioMenuSuperiorDirections
import cat.copernic.letmedoit.databinding.OpinionsUserTemplateBinding
import com.google.firebase.auth.FirebaseAuth


class OpinionsViewHolder(val binding: OpinionsUserTemplateBinding) : RecyclerView.ViewHolder(binding.root)  {

    val profileImage = binding.profileImage
    val serviceName = binding.serviceName
    val description = binding.description
    val rating = binding.userRating
    val nameSurname = binding.nameSurname

    val opinion = binding.opinionLayout

    fun render(opinionsModel: Opinions){
        //Asigación de datos a los controles del view.
        serviceName.text = opinionsModel.service
        description.text = opinionsModel.description
        rating.rating = opinionsModel.rating
        nameSurname.text = opinionsModel.user

        opinion.setOnClickListener { goToUser(opinionsModel.id) }

        if(FirebaseAuth.getInstance().currentUser == null){
            binding.btnReport.isEnabled = false
            binding.btnReport.isVisible = false
        }


        binding.btnReport.setOnClickListener { goToReport() }

    }

    private fun goToReport() {
        //action_perfilUsuarioMenuSuperior_to_userReport
        var action  = PerfilUsuarioMenuSuperiorDirections.actionPerfilUsuarioMenuSuperiorToUserReport()

        Navigation.findNavController(itemView).navigate(action)
    }

    private fun goToUser(id: String) {
        var action  = PerfilUsuarioMenuSuperiorDirections.actionPerfilUsuarioMenuSuperiorSelf(userID = id)

        Navigation.findNavController(itemView).navigate(action)
    }
}
