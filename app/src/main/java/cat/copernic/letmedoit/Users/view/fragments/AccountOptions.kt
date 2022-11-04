package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.letmedoit.Users.view.LenguagesProvider
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentOpcionesDeCuentaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountOptions.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountOptions : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding :FragmentOpcionesDeCuentaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpcionesDeCuentaBinding.inflate(inflater,container,false)

        val languagesString = ArrayList<String>()
        LenguagesProvider.obtenerLenguages().map { x -> x.lenguage }.toCollection(languagesString)
        Utils.AsignarPopUpSpinnerLenguages(requireContext(), languagesString, binding.spinnerLenguages)

        auth = Firebase.auth
        binding.btnSignOut.setOnClickListener{singOut()}

        return binding.root
    }

    private fun singOut() {
        auth.signOut()

    }
}