package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.presentation.adapter.admin.AdminReportAdapter
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.Utils.DataState
import cat.copernic.letmedoit.Utils.Utils
import cat.copernic.letmedoit.data.model.Users
import cat.copernic.letmedoit.databinding.FragmentAdminReportsBinding
import cat.copernic.letmedoit.presentation.viewmodel.general.ReportsViewModel
import cat.copernic.letmedoit.presentation.viewmodel.users.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class admin_reports : Fragment() {

    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.rotate_open_animation
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.rotate_close_animation
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.from_bottom_animation
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            binding.root.context,
            R.anim.to_bottom_animation
        )
    }

    private var open: Boolean = false

    private var _binding: FragmentAdminReportsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var reportMutableList = ArrayList<Report>()
    private lateinit var adapter: AdminReportAdapter
    private lateinit var llmanager: LinearLayoutManager
    private val viewModel: ReportsViewModel by viewModels()
    private lateinit var btnOpenMenu: FloatingActionButton
    private lateinit var btnDelete: FloatingActionButton
    private lateinit var btnArchived: FloatingActionButton
    private lateinit var btnBan: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminReportsBinding.inflate(inflater, container, false)

        llmanager = LinearLayoutManager(binding.root.context)
        recyclerView = binding.rcvAdminReportList
        init()

        viewModel.getReportState.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        reportMutableList =
                            ArrayList(dataState.data)
                        initRecyclerView()
                    }
                    is DataState.Error -> {
                        Utils.showOkDialog(
                            "Error: ",
                            requireContext(),
                            dataState.exception.message.toString()
                        )


                    }
                    is DataState.Loading -> {

                    }
                    else -> Unit
                }
            })

        viewModel.deleteReportState.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { dataState ->
                when (dataState) {
                    is DataState.Success<Boolean> -> {
                        Log.d("AdminCategories", dataState.data.toString())

                    }
                    is DataState.Error -> {
                        Utils.showOkDialog(
                            "Error: ",
                            requireContext(),
                            dataState.exception.message.toString()
                        )

                    }
                    is DataState.Loading -> {

                    }
                    else -> Unit
                }

            })


        btnOpenMenu = binding.flbuttonOpenMenu
        btnArchived = binding.flbuttonArchived
        btnDelete = binding.flbuttonDelete
        btnBan = binding.flbuttonBan

        binding.btnBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnOpenMenu.setOnClickListener {
            openFloatingMenu()
        }
        btnArchived.setOnClickListener {
            Toast.makeText(binding.root.context, "Hola", Toast.LENGTH_SHORT).show()
        }
        btnBan.setOnClickListener { }
        btnDelete.setOnClickListener {
            eliminarReportes()
        }


        return binding.root
    }

    private fun eliminarReportes() {
        var checkReportList = reportMutableList.filter { report -> report.check == true }
        checkReportList.forEachIndexed { i, report ->
            viewModel.deleteReport(report.id)
            reportMutableList.removeAt(i)
            adapter.notifyItemRemoved(i)
        }
    }


    private fun openFloatingMenu() {
        if (!open) {
            btnOpenMenu.startAnimation(rotateOpen)

            btnDelete.isVisible = true
            btnBan.isVisible = true
            btnDelete.isEnabled = true
            btnBan.isEnabled = true

            btnDelete.startAnimation(fromBottom)
            btnBan.startAnimation(fromBottom)

            open = true
        } else {
            btnDelete.isEnabled = false
            btnBan.isEnabled = false
            btnOpenMenu.startAnimation(rotateClose)
            btnDelete.startAnimation(toBottom)
            btnBan.startAnimation(toBottom)

            btnDelete.isVisible = false
            btnBan.isVisible = false
            open = false
        }
    }

    private fun initRecyclerView() {


        adapter = AdminReportAdapter(
            reportList = reportMutableList,
            onClickCheckBox = { report -> checkTheBox(report) },
        )
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }

    private fun checkTheBox(report: Report) {
        if (report.check) {
            report.check = false
        } else {
            report.check = true
        }
    }

    private fun init() {
        //hace: lee toda las categorias de la base de datos
        viewModel.getReports()
    }

}