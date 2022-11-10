package cat.copernic.letmedoit.Admin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminReportsBinding


class admin_reports : Fragment() {

    private var _binding: FragmentAdminReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminReportsBinding.inflate(inflater,container,false)
        binding.btnBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

}