package cat.copernic.letmedoit.Users.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.letmedoit.Users.view.LenguagesProvider
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentAdminViewUsersBinding
import cat.copernic.letmedoit.databinding.FragmentOpcionesDeCuentaBinding
import cat.copernic.letmedoit.databinding.FragmentViewServiceAdminBinding

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding :FragmentOpcionesDeCuentaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpcionesDeCuentaBinding.inflate(inflater,container,false)
        Utils.AsignarPopUpSpinner(context, LenguagesProvider.obtenerLenguages(), binding.spinnerLenguages)
        return binding.root
    }
}