package cat.copernic.letmedoit.General.view.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cat.copernic.letmedoit.General.model.ServiceProvider
import cat.copernic.letmedoit.General.model.adapter.CONS_ID
import cat.copernic.letmedoit.General.model.adapter.ServiceViewHolder
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.Utils.Utils.Companion.goToDestination
import cat.copernic.letmedoit.databinding.FragmentViewServiceBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [viewService.newInstance] factory method to
 * create an instance of this fragment.
 */
class viewService : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding : FragmentViewServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewServiceBinding.inflate(inflater,container,false)

        //Volver hacia atras
        binding.btnBack.setOnClickListener { requireActivity().onBackPressed() }

        initView(arguments?.getString(CONS_ID).toString())

        binding.btnGoToProfile.setOnClickListener { goToDestination(view,R.id.viewServiceToUserProfile) }
        return binding.root
    }

    private fun initView(id: String) {
        if (id == null)
            throw Exception("No hay ID")

        val service = ServiceProvider.getServices().filter { it.id == id }[0]
        Picasso.get().load(Uri.parse(service.image[0].img_link)).fit().centerCrop().into(binding.imageService)
        binding.tittleService.text = service.title
        binding.subTextCategory.text = service.category.id_category
        binding.descriptionService.text = service.description
        binding.txtCountFav.text = service.n_likes.toString()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment viewService.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            viewService().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}