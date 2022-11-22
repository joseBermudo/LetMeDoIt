package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.presentation.viewmodel.admin.CreateCategoryViewModel
import cat.copernic.letmedoit.presentation.adapter.admin.AdminCategoryAdapter
import cat.copernic.letmedoit.data.model.Category
import cat.copernic.letmedoit.data.model.Subcategory
import cat.copernic.letmedoit.data.provider.CategoryProvider
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.databinding.FragmentAdminCategoriesListBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AdminCategoriesList : Fragment() {
    private var _binding: FragmentAdminCategoriesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateCategoryViewModel by viewModels()

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminCategoriesListBinding.inflate(inflater, container, false)



        llmanager = LinearLayoutManager(binding.root.context)
        recyclerView = binding.rcvListaCategorias
        barraBusqueda = binding.searchViewAdminCategories

        initRecyclerView()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.newCategoryState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataState ->
            when (dataState) {
                is DataState.Success<Boolean> -> {
                    finishedProgress()
                }
                is DataState.Error -> {
                    Utils.showOkDialog("Error: ", requireContext(), dataState.exception.message.toString())
                    finishedProgress()
                }
                is DataState.Loading -> {
                    showProgress()
                }
                else -> Unit
            }
        })
        binding.btnAdd.setOnClickListener { createCategory() }
        return binding.root
    }


    private fun finishedProgress(){
        myDialog.dismiss()
    }
    private fun showProgress() {
        val btnAccept = dialogBinding.findViewById<Button>(R.id.btn_doneCreate)
        val btnCancel = dialogBinding.findViewById<Button>(R.id.btn_cancelCreateCategorie)
        val dialogTextBox = dialogBinding.findViewById<TextInputEditText>(R.id.txtInput_categoryName)
        val loading = dialogBinding.findViewById<ProgressBar>(R.id.newCategoryLoading)
        btnAccept.isEnabled = false
        btnCancel.isEnabled = false
        dialogTextBox.isEnabled = false
        loading.isVisible = true

    }

    lateinit var dialogBinding : View
    lateinit var myDialog : Dialog
    private fun createCategory() {

        dialogBinding = layoutInflater.inflate(R.layout.create_category_dialog, null)
        myDialog = Dialog(binding.root.context)
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
                val category = creteCategoryF(name)
                viewModel.insertCategory(category)
                categoryMutableList.add(index = 0, category)
                adapter.notifyItemInserted(0)
                llmanager.scrollToPositionWithOffset(0, 10)
            }
        }


    }

    private fun initRecyclerView() {
        adapter = AdminCategoryAdapter(categoryList = categoryMutableList,
            onClickListener = { category -> onItemSelected(category) },
            onClickDelete = { position -> onDeletedItem(position) },
            onClickEdit = { category -> onEditItem(category) })
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }

    private fun onItemSelected(category: Category) {
        val dialogBinding = layoutInflater.inflate(R.layout.show_category_description_dialog, null)
        val myDialog = Dialog(binding.root.context)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        dialogBinding.findViewById<TextView>(R.id.category_name).text = category.nombre
        dialogBinding.findViewById<TextView>(R.id.category_description).text = category.description
        myDialog.show()

        dialogBinding.findViewById<Button>(R.id.btn_closse_dialog).setOnClickListener {
            myDialog.dismiss()
        }
    }

    private fun onEditItem(category: Category) {
        val action = AdminCategoriesListDirections.actionAdminCategoriesListToAdminSubcategoryList(
            subcategories = category.subcategories.toTypedArray()
        )
        findNavController().navigate(action)
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



    private fun creteCategoryF(name: String): Category {
        return Category(
            name,
            "pepe",
            arrayListOf<Subcategory>(Subcategory("Pasear perros", "pepe", "100")),
            "favorites_icon",
            UUID.randomUUID().toString()
        )
    }
}