package cat.copernic.letmedoit.General.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import cat.copernic.letmedoit.General.model.data.Subcategory
import cat.copernic.letmedoit.Admin.model.adapter.AdminSubcategoryAdapter
import cat.copernic.letmedoit.databinding.FragmentAdminSubcategoryListBinding


class AdminSubcategoryList : Fragment() {

    private var _binding: FragmentAdminSubcategoryListBinding? = null
    private val binding get() = _binding!!
    val args: AdminSubcategoryListArgs by navArgs()
    lateinit private var subcateogryMutableList: MutableList<Subcategory>
    private lateinit var adapter: AdminSubcategoryAdapter
    private lateinit var llmanager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminSubcategoryListBinding.inflate(inflater, container, false)
        llmanager = LinearLayoutManager(binding.root.context)
        subcateogryMutableList = args.subcategories.toMutableList()
        initRecylerView()
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun initRecylerView() {
        adapter = AdminSubcategoryAdapter(subcategoryList = subcateogryMutableList)
        binding.rcvListaSubcategories.adapter = adapter
        binding.rcvListaSubcategories.layoutManager = llmanager
    }

}