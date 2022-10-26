package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.CategoryProvider
import cat.copernic.letmedoit.General.model.adapter.AdminCategoryAdapter
import cat.copernic.letmedoit.General.model.adapter.CategoryAdapter
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminCategoriesListBinding


class AdminCategoriesList : Fragment() {
    private var _binding: FragmentAdminCategoriesListBinding? = null
    private val binding get() = _binding!!
    private var recyclerGrid = false
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentAdminCategoriesListBinding.inflate(inflater,container, false)
        initRecyclerView()
        recyclerView = binding.rcvListaCategorias
        binding.btnRecyclerMode.setOnClickListener {
            selectLayoutReyclerMode()

        }
        return binding.root
    }

    private fun selectLayoutReyclerMode(){
        if(recyclerGrid){
            recyclerGrid = false
            recyclerView.layoutManager = GridLayoutManager(binding.root.context,3)
            binding.btnRecyclerMode.setBackgroundResource(R.drawable.ic_baseline_format_list_bulleted_24)
        }else{
            recyclerGrid = true
            recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.btnRecyclerMode.setBackgroundResource(R.drawable.ic_baseline_grid_view_24)

        }
    }

    private fun initRecyclerView(){
        //val manager = GridLayoutManager(binding.root.context,2)

        binding.rcvListaCategorias.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rcvListaCategorias.adapter = AdminCategoryAdapter(CategoryProvider.obtenerCategorias()) { category ->
            onItemSelected(
                category
            )
        }
    }

    fun onItemSelected(category: Category){
        Toast.makeText(binding.root.context,category.nombre,Toast.LENGTH_SHORT).show()
    }
}