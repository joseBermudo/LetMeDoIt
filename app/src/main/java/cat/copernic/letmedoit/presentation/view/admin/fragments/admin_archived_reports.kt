package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminArchivedReportsBinding


class admin_archived_reports : Fragment() {

    private var _binding: FragmentAdminArchivedReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminArchivedReportsBinding.inflate(inflater,container,false)

        binding.btnArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }


}