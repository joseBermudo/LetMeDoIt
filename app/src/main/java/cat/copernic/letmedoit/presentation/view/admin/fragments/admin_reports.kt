package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.presentation.adapter.admin.AdminReportAdapter
import cat.copernic.letmedoit.data.provider.ReportProvider
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.R
import cat.copernic.letmedoit.databinding.FragmentAdminReportsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


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
    private var reportMutableList: MutableList<Report> =
        ReportProvider.obtenerReportes().toMutableList()
    private lateinit var adapter: AdminReportAdapter
    private lateinit var llmanager: LinearLayoutManager

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

        initRecyclerView()

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
        btnDelete.setOnClickListener { }


        return binding.root
    }

    private fun openFloatingMenu(){
        if(!open){
            btnOpenMenu.startAnimation(rotateOpen)

            btnDelete.isVisible = true
            btnBan.isVisible = true
            btnArchived.isVisible = true
            btnDelete.isEnabled = true
            btnBan.isEnabled = true
            btnArchived.isEnabled = true
            btnDelete.startAnimation(fromBottom)
            btnBan.startAnimation(fromBottom)
            btnArchived.startAnimation(fromBottom)
            open = true
        }else{
            btnDelete.isEnabled = false
            btnBan.isEnabled = false
            btnArchived.isEnabled = false
            btnOpenMenu.startAnimation(rotateClose)
            btnDelete.startAnimation(toBottom)
            btnBan.startAnimation(toBottom)
            btnArchived.startAnimation(toBottom)

            btnDelete.isVisible = false
            btnBan.isVisible = false
            btnArchived.isVisible = false
            open = false
        }
    }

    private fun initRecyclerView() {
        adapter = AdminReportAdapter(
            reportList = reportMutableList
        )
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }

}