package cat.copernic.letmedoit.presentation.view.general.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cat.copernic.letmedoit.databinding.FragmentProfileMoreInfoBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileMoreInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileMoreInfo : Fragment() {
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

    //TODO: Implementar parametros basados en la base de datos
    lateinit var binding: FragmentProfileMoreInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileMoreInfoBinding.inflate(inflater,container,false)
        binding.btnPdf.setOnClickListener{ openPDF("https://www.soundczech.cz/temp/lorem-ipsum.pdf") }
        binding.btnEmail.setOnClickListener { sendEmail("alexcruceat@gmail.com") }
        binding.btnMobile.setOnClickListener { callUser("648551479") }
        binding.locationIcon.setOnClickListener { openMaps("Carretera de Matadepera") }
        return binding.root
    }

    private fun openPDF( url : String){

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)


        startActivity(browserIntent)
    }
    private fun sendEmail(addresses : String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:") // only email apps should handle this

        intent.putExtra(Intent.EXTRA_EMAIL, addresses)

        startActivity(Intent.createChooser(intent,
            "Send Email Using: "))
    }
    private fun callUser(telf : String){
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:${telf}")
        startActivity(Intent.createChooser(dialIntent,
            "Call Using: "))
    }
    private fun openMaps(location : String){
        val gmmIntentUri =  Uri.parse("geo:0,0?q=${location}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(Intent.createChooser(mapIntent,
            "Show Map Using: "))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileMoreInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileMoreInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}