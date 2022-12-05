package cat.copernic.letmedoit.presentation.view.users.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Constants
import cat.copernic.letmedoit.databinding.FragmentEditarInformacionPerfilBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditarInformacionPerfil : Fragment() {


    val args : EditarInformacionPerfilArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var binding : FragmentEditarInformacionPerfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditarInformacionPerfilBinding.inflate(inflater,container,false)

        initView()
        binding.backArrow.setOnClickListener{requireActivity().onBackPressed()}
        return binding.root
    }

    private fun initView() {
        val user =  args.user

        Picasso.get().load(user.avatar).into(binding.profileImage)
        binding.nameSurname.text = "${user.name} ${user.surname} \n @${user.username} \n"
        binding.aboutMeText.text = user.aboutMe
        binding.scheduleText.text = user.schedule.toString()
        binding.locationText.text = user.location
    }
}