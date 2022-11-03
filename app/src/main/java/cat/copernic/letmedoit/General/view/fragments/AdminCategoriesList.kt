package cat.copernic.letmedoit.General.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.General.model.Category
import cat.copernic.letmedoit.General.model.CategoryProvider
import cat.copernic.letmedoit.General.model.Subcategory
import cat.copernic.letmedoit.General.model.adapter.AdminCategoryAdapter
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminCategoriesListBinding
import com.google.android.material.textfield.TextInputEditText


class AdminCategoriesList : Fragment() {
    private var _binding: FragmentAdminCategoriesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var categoryMutableList: MutableList<Category> =
        CategoryProvider.obtenerCategorias().toMutableList()
    private lateinit var adapter: AdminCategoryAdapter
    private lateinit var llmanager: LinearLayoutManager
    private lateinit var barraBusqueda: android.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminCategoriesListBinding.inflate(inflater, container, false)

        llmanager = LinearLayoutManager(binding.root.context)
        recyclerView = binding.rcvListaCategorias
        barraBusqueda = binding.searchViewAdminCategories

        initRecyclerView()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnAdd.setOnClickListener { createCategory() }
        return binding.root
    }

    private fun createCategory() {

        val dialogBinding = layoutInflater.inflate(R.layout.create_category_dialog, null)
        val myDialog = Dialog(binding.root.context)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.show()

        val btn_cancel = dialogBinding.findViewById<Button>(R.id.btn_cancelCreateCategorie)
        btn_cancel.setOnClickListener {
            myDialog.dismiss()
        }

        val btn_done = dialogBinding.findViewById<Button>(R.id.btn_doneCreate)

        btn_done.setOnClickListener {
            val txtInput_name =
                dialogBinding.findViewById<TextInputEditText>(R.id.txtInput_categoryName)
            val name = txtInput_name.text.toString().trim()
            if (!name.isEmpty() && !name.isBlank()) {
                val category = Category(
                    name,
                    arrayListOf<Subcategory>(Subcategory("Pasear perros", "100")),
                    "favorites_icon",
                    "3"
                )
                categoryMutableList.add(index = 0, category)
                adapter.notifyItemInserted(0)
                llmanager.scrollToPositionWithOffset(0, 10)
                myDialog.dismiss()
            }
        }


    }

    private fun initRecyclerView() {
        adapter = AdminCategoryAdapter(
            categoryList = categoryMutableList,
            onClickListener = { category -> onItemSelected(category) },
            onClickDelete = { position -> onDeletedItem(position) })
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }

    private fun onItemSelected(category: Category) {
        Toast.makeText(binding.root.context, category.nombre, Toast.LENGTH_SHORT).show()
    }

    private fun onDeletedItem(position: Int) {

        val dialogBinding = layoutInflater.inflate(R.layout.delete_category_alert_dialog, null)
        val myDialog = Dialog(binding.root.context)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.show()

        val btn_cancel = dialogBinding.findViewById<Button>(R.id.btn_cancelDeleteCategory)
        val btn_accept = dialogBinding.findViewById<Button>(R.id.btn_acceptDeleteCategory)

        btn_cancel.setOnClickListener { myDialog.dismiss() }

        btn_accept.setOnClickListener {
            categoryMutableList.removeAt(position)
            adapter.notifyItemRemoved(position)
            myDialog.dismiss()
        }

    }
}