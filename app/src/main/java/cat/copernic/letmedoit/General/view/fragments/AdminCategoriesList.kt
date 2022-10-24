package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.CategoryProvider
import cat.copernic.letmedoit.General.model.adapter.AdminCategoryAdapter
import cat.copernic.letmedoit.databinding.FragmentAdminCategoriesListBinding

class AdminCategoriesList : Fragment() {
    private var _binding: FragmentAdminCategoriesListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentAdminCategoriesListBinding.inflate(inflater,container, false)
        initRecyclerView()
        return binding.root
    }


    private fun initRecyclerView(){

        binding.rcvListaCategorias.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rcvListaCategorias.adapter = AdminCategoryAdapter(CategoryProvider.obtenerCategorias())
    }
}