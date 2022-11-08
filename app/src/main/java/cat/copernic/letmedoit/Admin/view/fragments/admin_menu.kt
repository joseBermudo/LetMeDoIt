package cat.copernic.letmedoit.Admin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminMenuBinding


class admin_menu : Fragment() {

    private var _binding: FragmentAdminMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminMenuBinding.inflate(inflater, container, false)

        binding.btnCategories.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_adminCategoriesList)
        }

        binding.btnUsers.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_view_users)
        }

        binding.btnReports.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_reports)
        }

        binding.btnArchivedReports.setOnClickListener {
            findNavController().navigate(R.id.action_admin_menu_to_admin_archived_reports)
        }

        return binding.root
    }


}