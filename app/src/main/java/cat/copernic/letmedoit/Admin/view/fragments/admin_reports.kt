package cat.copernic.letmedoit.Admin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.Admin.model.adapter.AdminReportAdapter
import cat.copernic.letmedoit.Admin.model.provider.ReportProvider
import cat.copernic.letmedoit.General.model.data.Report
import cat.copernic.letmedoit.databinding.FragmentAdminReportsBinding


class admin_reports : Fragment() {

    private var _binding: FragmentAdminReportsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var reportMutableList: MutableList<Report> =
        ReportProvider.obtenerReportes().toMutableList()
    private lateinit var adapter: AdminReportAdapter
    private lateinit var llmanager: LinearLayoutManager


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

        binding.btnBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = AdminReportAdapter(
            reportList = reportMutableList
        )
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }

}