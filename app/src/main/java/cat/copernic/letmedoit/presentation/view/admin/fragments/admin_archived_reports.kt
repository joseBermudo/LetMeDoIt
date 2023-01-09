package cat.copernic.letmedoit.presentation.view.admin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.letmedoit.data.model.Report
import cat.copernic.letmedoit.databinding.FragmentAdminArchivedReportsBinding
import cat.copernic.letmedoit.presentation.adapter.admin.AdminArchivedReportsAdapter


class admin_archived_reports : Fragment() {

    private var _binding: FragmentAdminArchivedReportsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var reportMutableList = ArrayList<Report>()

    private lateinit var adapter: AdminArchivedReportsAdapter
    private lateinit var llmanager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminArchivedReportsBinding.inflate(inflater, container, false)


        llmanager = LinearLayoutManager(binding.root.context)
        recyclerView = binding.rcvAdminArchivedReportList

        initRecyclerView()

        binding.btnArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = AdminArchivedReportsAdapter(
            reportList = reportMutableList
        )
        recyclerView.layoutManager = llmanager
        recyclerView.adapter = adapter
    }


}